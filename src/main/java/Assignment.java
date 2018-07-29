import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by JoeL on 28/07/2018.
 */
public class Assignment {
    private static final String STARTED = "STARTED";
    private static final String FINISHED = "FINISHED";
    private static final String ALARM = "ALARM";
    private static Long STARTDATE = 4688534123L;

    public static void main (String[] argv) throws SQLException, IOException {
        if (argv.length != 1 || argv[0].isEmpty()){
            System.err.println("one argument required");
            System.exit(1);
        }

        LogHandler logHandler = new LogHandler();
        EntryDbHandler entryDbHandler = new EntryDbHandler();

        List<String> lineList = logHandler.readLog(argv[0]);
        List<Entry> entries = new ArrayList<>();

        for (String line : lineList){
            JsonNodeMapper jsonNodeMapper = new JsonNodeMapper();
            jsonNodeMapper.JsonNodeMapperInit(line);
            entries.add(jsonNodeMapper.setEntry());
        }

        ArrayList<String> uniqId = new ArrayList<>();
        Map<String, List<Entry>> entriesById = entries.stream().collect(Collectors.groupingBy(Entry::getId));
        entriesById.values().stream()
                .filter(entriesWithSameId -> entriesWithSameId.size()>1)
                .forEach(entriesWithSameId -> uniqId.add(entriesWithSameId.get(0).getId()));

        List<Map<String,String>> stateTime = new ArrayList<>();
        for (int index = 0; index < uniqId.size(); index++){
            String id = uniqId.get(index);
            stateTime.add(entries.stream()
                    .filter(s -> (id).equals(s.getId()))
                    .collect(Collectors.toMap(
                                    s -> ((Entry) s).getTimestamp(),
                                    s -> ((Entry) s).getState())
                    ));
        }

       List<Map<String,String>> alarmMap = new ArrayList<>();
       stateTime.forEach((s) -> alarmMap.add(longEvent(s)));

        entryDbHandler.createDb();
        entries.forEach(entry -> {
            try {
                entryDbHandler.insertRecord(entry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        for (Map<String,String> record: alarmMap){
            if (!record.isEmpty()){
                for (Map.Entry<String,String> jotting : record.entrySet()){
                    System.out.println(jotting.getValue()+ " @ " +jotting.getKey());
                    entryDbHandler.alertEntry(jotting.getKey());
                }
            }
        }
        entryDbHandler.lookupAlert();
        entryDbHandler.destroy();
    }

    private static Map<String, String> longEvent(Map<String,String> map){
        Map<String,String> eventTimeStamp = new HashMap<>();
        for (Map.Entry<String,String> item: map.entrySet()){
           if (item.getValue().equals(STARTED)){
               STARTDATE = Long.parseLong(item.getKey());
           }
            if (item.getValue().equals(FINISHED)){
                Long finishedDate = Long.parseLong(item.getKey());
                if (finishedDate - STARTDATE > 4){
                    eventTimeStamp.put(item.getKey(),ALARM);
                }
            }
        }
        return eventTimeStamp;
    }

}
