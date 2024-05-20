package zest;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;

public class MovieStreamingTest {
    private FileStreamService fileStreamService;
    private CacheService cacheService;
    private MovieStreamingManager movieStreamingManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fileStreamService = mock(FileStreamService.class);
        cacheService = mock(CacheService.class);

        movieStreamingManager = new MovieStreamingManager(fileStreamService, cacheService);
    }

    @Test
    public void testStreamMovieFromCache() {
        String movieId = "id1";
        String movieTitle = "title1";
        String movieDescription = "description1";
        String streamToken = "token1";
        MovieMetadata movieMetadata = new MovieMetadata(movieTitle, movieDescription);
        StreamingDetails cachedDetails = new StreamingDetails(movieId, streamToken, movieMetadata);

        // the returned cacheDetails and the result must be the same
        when(cacheService.getDetails(movieId)).thenReturn(cachedDetails);
        StreamingDetails result = movieStreamingManager.streamMovie(movieId);

        // if it is a hit, the details must be retrieved from the cache
        // file stream service must not be used
        verify(cacheService).getDetails(movieId);
        verifyNoInteractions(fileStreamService);
        assertEquals(cachedDetails, result);
    }

    @Test
    public void testStreamMovieFromFile() {
        String movieId = "id2";
        String movieTitle = "title2";
        String movieDescription = "description2";
        String streamToken = "token2";
        MovieMetadata movieMetadata = new MovieMetadata(movieTitle, movieDescription);
        StreamingDetails expectedDetails = new StreamingDetails(movieId, streamToken, movieMetadata);

        when(cacheService.getDetails(movieId)).thenReturn(null);
        when(fileStreamService.retrieveMovie(movieId)).thenReturn(movieMetadata);
        when(fileStreamService.generateToken(movieId)).thenReturn(streamToken);

        StreamingDetails result = movieStreamingManager.streamMovie(movieId);

        verify(cacheService).getDetails(movieId);
        verify(fileStreamService).retrieveMovie(movieId);
        verify(fileStreamService).generateToken(movieId);

        // cannot assertEquals() for two different objects
        // instead use argument captor and compare their values
        ArgumentCaptor<StreamingDetails> captor = ArgumentCaptor.forClass(StreamingDetails.class);
        verify(cacheService).cacheDetails(eq(movieId), captor.capture());
        StreamingDetails cachedDetails = captor.getValue();

        assertEquals(expectedDetails.getMovieId(), cachedDetails.getMovieId());
        assertEquals(expectedDetails.getStreamToken(), cachedDetails.getStreamToken());
        assertEquals(expectedDetails.getMetadata(), cachedDetails.getMetadata());
    }

    @Test
    public void testFileStreamServiceError() {
        String movieId = "id3";

        when(cacheService.getDetails(movieId)).thenReturn(null);
        when(fileStreamService.retrieveMovie(movieId)).thenThrow(new RuntimeException("File stream service error"));

        try {
            movieStreamingManager.streamMovie(movieId);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("File stream service error", e.getMessage());
        }

        verify(cacheService).getDetails(movieId);
        verify(fileStreamService).retrieveMovie(movieId);
        verifyNoMoreInteractions(fileStreamService);
        verifyNoMoreInteractions(cacheService);
    }

    @Test
    public void testStreamMovieTokenGenerationError() {
        String movieId = "id4";
        String movieTitle = "title4";
        String movieDescription = "description4";
        MovieMetadata movieMetadata = new MovieMetadata(movieTitle, movieDescription);

        when(cacheService.getDetails(movieId)).thenReturn(null);
        when(fileStreamService.retrieveMovie(movieId)).thenReturn(movieMetadata);
        when(fileStreamService.generateToken(movieId)).thenThrow(new RuntimeException("Token generation error"));

        try {
            movieStreamingManager.streamMovie(movieId);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Token generation error", e.getMessage());
        }

        verify(cacheService).getDetails(movieId);
        verify(fileStreamService).retrieveMovie(movieId);
        verify(fileStreamService).generateToken(movieId);
        verifyNoMoreInteractions(fileStreamService);
    }
}
