import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

public class JsonNodeMapper {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    JsonNode rootNode;
    ObjectMapper objectMapper;
    Entry entry = new Entry();

    public JsonNode JsonNodeMapperInit(String line){
        objectMapper = new ObjectMapper();
        try {
            rootNode = objectMapper.readTree(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    public JsonNode readJsonWithJsonNode() throws IOException {
        String printEntry = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        logger.info(printEntry+"\n");
        return rootNode;
    }

    public String readIdNode()
    {
        JsonNode idNode=rootNode.path("id");
        entry.setId(idNode.asText());
        return idNode.asText();
    }

    public String readStateNode()
    {
        JsonNode stateNode=rootNode.path("state");
        String st=stateNode.asText();
        entry.setState(st);
        return st;
    }

    public String readTypeNode()
    {
        JsonNode typeNode=rootNode.path("type");
        String type=typeNode.asText();
        entry.setType(type);
        return type;
    }

    public String readHostNode()
    {
        JsonNode hostNode=rootNode.path("host");
        String host=hostNode.asText();
        entry.setHost(host);
        return host;
    }

    public String readTStamp(){
        JsonNode tsNode = rootNode.path("timestamp");
        String tStamp = tsNode.asText();
        entry.setTimestamp(tStamp);
        return tStamp;
    }

    public Entry setEntry(){
        entry.setHost(readHostNode());
        entry.setId(readIdNode());
        entry.setState(readStateNode());
        entry.setType(readTypeNode());
        entry.setTimestamp(readTStamp());
        return entry;
    }
}
