import org.junit.Test;

public class LogHandlerTest {
    private static final String path = "src/main/resources/server.log";

    @Test
    public void testLogHandler() throws Exception {
       LogHandler logHandler = new LogHandler();
       System.out.println(logHandler.readLog(path));
    }
}
