import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CatFactsTest {

    private HttpUtil httpUtil;
    private CatFactsRetriever catFactsRetriever;

    @BeforeEach
    public void setup() {
        httpUtil = Mockito.mock(HttpUtil.class);
        catFactsRetriever = new CatFactsRetriever(httpUtil);
    }

    @Test
    public void testRetrieveRandom() throws IOException {
        when(httpUtil.get("https://catfact.ninja/fact")).thenReturn("{\"fact\":\"Cats are cute.\", \"length\": 13}");
        assertEquals("Cats are cute.", catFactsRetriever.retrieveRandom());
    }

    @Test
    public void testRetrieveRandomEmpty() throws IOException {
        when(httpUtil.get("https://catfact.ninja/fact")).thenReturn("{\"fact\":\"\", \"length\": 0}");
        assertEquals("", catFactsRetriever.retrieveRandom());
    }

    @Test
    public void testRetrieveLongest() throws IOException {
        when(httpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn("{\"data\":[{\"fact\":\"Cats are cute.\", \"length\": 13},{\"fact\":\"Cats are very cute.\", \"length\": 18},{\"fact\":\"Cats are extremely cute.\", \"length\": 23}]}");
        assertEquals("Cats are extremely cute.", catFactsRetriever.retrieveLongest(3));
    }

    @Test
    public void testRetrieveLongestEmpty() throws IOException {
        when(httpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn("{\"data\":[]}");
        assertEquals("", catFactsRetriever.retrieveLongest(3));
    }

    @Test
    public void testRetrieveLongestSingleFact() throws IOException {
        when(httpUtil.get("https://catfact.ninja/facts?limit=1")).thenReturn("{\"data\":[{\"fact\":\"Cats are cute.\", \"length\": 13}]}");
        assertEquals("Cats are cute.", catFactsRetriever.retrieveLongest(1));
    }

    @Test
    public void testRetrieveLongestZeroFact() throws IOException {
        when(httpUtil.get("https://catfact.ninja/facts?limit=0")).thenReturn("{\"data\":[{\"fact\":\"Cats are cute.\", \"length\": 13},{\"fact\":\"Cats are very cute.\", \"length\": 18},{\"fact\":\"Cats are extremely cute.\", \"length\": 23}]}");
        assertEquals("", catFactsRetriever.retrieveLongest(0));
    }

    @Test
    public void testRetrieveLongestNegativeFact() throws IOException {
        when(httpUtil.get("https://catfact.ninja/facts?limit=-1")).thenReturn("{\"data\":[{\"fact\":\"Cats are cute.\", \"length\": 13},{\"fact\":\"Cats are very cute.\", \"length\": 18},{\"fact\":\"Cats are extremely cute.\", \"length\": 23}]}");
        assertEquals("", catFactsRetriever.retrieveLongest(-1));
    }
}