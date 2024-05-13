
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.IOException;




public class CatFactsTest {

    private HttpUtil httpUtil;

    @BeforeEach
    public void setup() {
        httpUtil = Mockito.mock(HttpUtil.class);
    }

    @Test
    public void example() throws IOException{
        when(httpUtil.get("https://catfact.ninja/fact")).thenReturn("{\"fact\":\"Cats are cute.\", \"length\": 13}");

        CatFactsRetriever catFactsRetriever = new CatFactsRetriever(httpUtil);

        assertEquals("Cats are cute.", catFactsRetriever.retrieveRandom());



    }
}