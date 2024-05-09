import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;





public class BookReviewsTest {

    @Test
    public void testHighRatedBooks() {
        // Create a mock BookRatingsFetcher
        BookRatingsFetcher fetcher = Mockito.mock(BookRatingsFetcher.class);

        // Create some real books
        Book book1 = new Book("Book 1", 5);
        Book book2 = new Book("Book 2", 2);
        Book book3 = new Book("Book 3", 4);

        // Set up the fetcher to return the mock books
        when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));

        // Create a BookManager and call highRatedBooks
        BookManager manager = new BookManager();
        List<Book> highRatedBooks = manager.highRatedBooks(fetcher);

        // Assert that only the high-rated books were returned
        assertEquals(2, highRatedBooks.size());
        assertEquals(Arrays.asList(book1, book3), highRatedBooks);

        // Verify that the fetcher was closed
        Mockito.verify(fetcher).close();
    }
}