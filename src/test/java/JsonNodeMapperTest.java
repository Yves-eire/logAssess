import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import java.util.Iterator;
import java.util.Map;
import static org.junit.Assert.*;


public class JsonNodeMapperTest {

    String jsonString = "{\"id\": \"scsmbstgra\",\"state\": \"STARTED\", \"type\": \"APPLICATION_LOG\", \"host\":\"12345\",\"timestamp\":\"1491377495242\"}";
    String jsonLine = "{\"id\": \"scsmbstgrc\", \"state\":\"STARTED\", \"timestamp\":\"1491377495210\"}";


    @Test
    public void testReadAppLogWithJsonNodeMapper() throws Exception {
        JsonNodeMapper jsonNodeMapper=new JsonNodeMapper(jsonString);
        JsonNode rootNode=jsonNodeMapper.readJsonWithJsonNode();
        assertNotNull(rootNode);

        Entry entry=jsonNodeMapper.getEntry();

        String id=jsonNodeMapper.readIdNode();
        assertEquals(id, entry.getId());
        assertEquals("scsmbstgra", id);

        String state=jsonNodeMapper.readStateNode();
        assertEquals(state, entry.getState());
        assertEquals("STARTED", state);

        String type=jsonNodeMapper.readTypeNode();
        assertEquals(type, entry.getType());
        assertEquals("APPLICATION_LOG", type);

        String host=jsonNodeMapper.readHostNode();
        assertEquals(host, entry.getHost());
        assertEquals("12345", host);

        String tStamp = jsonNodeMapper.readTStamp();
        assertEquals(tStamp, entry.getTimestamp());
        assertEquals( "1491377495242", tStamp );
    }

    @Test
    public void testReadSerLogWithJsonNodeMapper() throws Exception{
        JsonNodeMapper jsonNodeMapper=new JsonNodeMapper(jsonLine);
        Entry entry=jsonNodeMapper.getEntry();

        String id=jsonNodeMapper.readIdNode();
        assertEquals(id, entry.getId());
        assertEquals("scsmbstgrc", id);

        String state=jsonNodeMapper.readStateNode();
        assertEquals(state, entry.getState());
        assertEquals("STARTED", state);

        String tStamp = jsonNodeMapper.readTStamp();
        assertEquals(tStamp, entry.getTimestamp());
        assertEquals( "1491377495210", tStamp );
    }

}
