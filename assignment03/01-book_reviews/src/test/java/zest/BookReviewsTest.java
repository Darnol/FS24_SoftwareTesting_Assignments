import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;





public class BookReviewsTest {

    private BookRatingsFetcher fetcher;
    private BookManager manager;

    @BeforeEach
    public void setup() {
        fetcher = Mockito.mock(BookRatingsFetcher.class);
        manager = new BookManager();
    }

    @AfterEach
    public void teardown() {
        // Verify that the fetcher was closed
        Mockito.verify(fetcher).close();
    }  

    @Test
    public void testHighRatedBooksMixed() {

        // Create some real books
        Book book1 = new Book("Book 1", 5, "Author 1");
        Book book2 = new Book("Book 2", 2, "Author 2");
        Book book3 = new Book("Book 3", 4, "Author 3");

        // Set up the fetcher mock to return the mock books
        when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));

        List<Book> highRatedBooks = manager.highRatedBooks(fetcher);

        // Assert that only the high-rated books were returned
        assertEquals(2, highRatedBooks.size());
        assertEquals(Arrays.asList(book1, book3), highRatedBooks);

    }


    
        @Test
        public void testHighRatedBooksWhenAllBooksAreHighRated() {
    
            Book book1 = new Book("Book 1", 5, "Author 1");
            Book book2 = new Book("Book 2", 4, "Author 2");
            Book book3 = new Book("Book 3", 5, "Author 3");
    
            when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));
    
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that all books were returned
            assertEquals(3, highRatedBooks.size());
            assertEquals(Arrays.asList(book1, book2, book3), highRatedBooks);
    
        }
    
        @Test
        public void testHighRatedBooksWhenNoBooksAreHighRated() {
    
            Book book1 = new Book("Book 1", 3, "Author 1");
            Book book2 = new Book("Book 2", 2, "Author 2");
            Book book3 = new Book("Book 3", 1, "Author 3");
    
            when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));
    
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that no books were returned
            assertEquals(0, highRatedBooks.size());
    
        }
    
        @Test
        public void testHighRatedBooksWhenThereAreNoBooks() {
    
            // Set up the fetcher to return no books
            when(fetcher.all()).thenReturn(Collections.emptyList());
    
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that no books were returned
            assertEquals(0, highRatedBooks.size());
    
        }

        @Test
        public void testHighRatedBooksWhenOnlyOneBook() {
    
            Book book1 = new Book("Book 1", 5, "Author 1");
    
            when(fetcher.all()).thenReturn(Arrays.asList(book1));
    
            List<Book> highRatedBooks = manager.highRatedBooks(fetcher);
    
            // Assert that the high-rated book was returned
            assertEquals(1, highRatedBooks.size());
            assertEquals(Arrays.asList(book1), highRatedBooks);
    
        }

 
}