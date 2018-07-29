import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import java.util.Iterator;
import java.util.Map;
import static org.junit.Assert.*;


public class JsonNodeMapperTest {

    String jsonString = "{\"id\": \"scsmbstgra\",\"state\": \"STARTED\", \"type\": \"APPLICATION_LOG\", \"host\":\"12345\",\"timestamp\":\"1491377495242\"}";
    String jsonLine = "{\"id\": \"scsmbstgrc\", \"state\":\"STARTED\", \"timestamp\":\"1491377495210\"}";
    Entry logEntry1 = new Entry();
    Entry logEntry2 = new Entry();


    @Test
    public void testReadAppLogWithJsonNodeMapper() throws Exception {
        JsonNodeMapper jsonNodeMapper=new JsonNodeMapper();
        jsonNodeMapper.JsonNodeMapperInit(jsonString);
        JsonNode rootNode=jsonNodeMapper.readJsonWithJsonNode();
        assertNotNull(rootNode);

        String id=jsonNodeMapper.readIdNode();
        assertEquals(id, jsonNodeMapper.entry.getId());
        assertEquals("scsmbstgra", id);

        String state=jsonNodeMapper.readStateNode();
        assertEquals(state, jsonNodeMapper.entry.getState());
        assertEquals("STARTED", state);

        String type=jsonNodeMapper.readTypeNode();
        assertEquals(type, jsonNodeMapper.entry.getType());
        assertEquals("APPLICATION_LOG", type);

        String host=jsonNodeMapper.readHostNode();
        assertEquals(host, jsonNodeMapper.entry.getHost());
        assertEquals("12345", host);

        String tStamp = jsonNodeMapper.readTStamp();
        assertEquals(tStamp, jsonNodeMapper.entry.getTimestamp());
        assertEquals( "1491377495242", tStamp );
    }

    @Test
    public void testReadSerLogWithJsonNodeMapper() throws Exception{
        JsonNodeMapper jsonNodeMapper=new JsonNodeMapper();
        jsonNodeMapper.JsonNodeMapperInit(jsonLine);

        String id=jsonNodeMapper.readIdNode();
        assertEquals(id, jsonNodeMapper.entry.getId());
        assertEquals("scsmbstgrc", id);

        String state=jsonNodeMapper.readStateNode();
        assertEquals(state, jsonNodeMapper.entry.getState());
        assertEquals("STARTED", state);

        String tStamp = jsonNodeMapper.readTStamp();
        assertEquals(tStamp, jsonNodeMapper.entry.getTimestamp());
        assertEquals( "1491377495210", tStamp );
    }

}
