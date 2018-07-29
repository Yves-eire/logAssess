import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogHandler {
    private static final String path = "src/main/resources/server.log";
    List<String> lineList = new ArrayList<>();

    public List<String> readLog(String filePath) throws IOException
    {
        if (filePath == null || filePath.isEmpty()){
            filePath = path;
        }
        try( FileInputStream logStream = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader( new InputStreamReader(logStream)))
        {
            String line;
            while(( line = br.readLine()) != null ) {
                lineList.add(line);
            }
            return lineList;
        }
    }
}
