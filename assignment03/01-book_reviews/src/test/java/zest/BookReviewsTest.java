import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;





public class BookReviewsTest {

    @Test
    public void testHighRatedBooksMixed() {
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


    
        @Test
        public void testHighRatedBooksWhenAllBooksAreHighRated() {
            // Create a mock BookRatingsFetcher
            BookRatingsFetcher fetcher = Mockito.mock(BookRatingsFetcher.class);
    
            // Create some real books
            Book book1 = new Book("Book 1", 5);
            Book book2 = new Book("Book 2", 4);
            Book book3 = new Book("Book 3", 5);
    
            // Set up the fetcher to return the mock books
            when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));
    
            // Create a BookManager and call highRatedBooks
            BookManager manager = new BookManager();
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that all books were returned
            assertEquals(3, highRatedBooks.size());
            assertEquals(Arrays.asList(book1, book2, book3), highRatedBooks);
    
            // Verify that the fetcher was closed
            Mockito.verify(fetcher).close();
        }
    
        @Test
        public void testHighRatedBooksWhenNoBooksAreHighRated() {
            // Create a mock BookRatingsFetcher
            BookRatingsFetcher fetcher = Mockito.mock(BookRatingsFetcher.class);
    
            // Create some real books
            Book book1 = new Book("Book 1", 3);
            Book book2 = new Book("Book 2", 2);
            Book book3 = new Book("Book 3", 1);
    
            // Set up the fetcher to return the mock books
            when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));
    
            // Create a BookManager and call highRatedBooks
            BookManager manager = new BookManager();
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that no books were returned
            assertEquals(0, highRatedBooks.size());
    
            // Verify that the fetcher was closed
            Mockito.verify(fetcher).close();
        }
    
        @Test
        public void testHighRatedBooksWhenThereAreNoBooks() {
            // Create a mock BookRatingsFetcher
            BookRatingsFetcher fetcher = Mockito.mock(BookRatingsFetcher.class);
    
            // Set up the fetcher to return no books
            when(fetcher.all()).thenReturn(Collections.emptyList());
    
            // Create a BookManager and call highRatedBooks
            BookManager manager = new BookManager();
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that no books were returned
            assertEquals(0, highRatedBooks.size());
    
            // Verify that the fetcher was closed
            Mockito.verify(fetcher).close();
        }
    
}