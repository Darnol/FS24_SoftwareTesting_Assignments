import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


public class UniqueAuthorsTest {
    
    private BookAuthorFetcher fetcher;
    private BookManager manager;

    @BeforeEach
    public void setup() {
        fetcher = Mockito.mock(BookAuthorFetcher.class);
        manager = new BookManager();
    }

    @AfterEach
    public void teardown() {
        // Verify that the fetcher was closed
        Mockito.verify(fetcher).close();
    }  

    @Test
    public void testUniqueAuthorsWhenAllAuthorsAreUnique() {
        // Create some real books
        Book book1 = new Book("Book 1", 5, "Author 1");
        Book book2 = new Book("Book 2", 4, "Author 2");
        Book book3 = new Book("Book 3", 5, "Author 3");

        // Set up the fetcher to return the mock books
        when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));

        // Call uniqueAuthors
        List<String> uniqueAuthors = manager.uniqueAuthors(fetcher);

        // Assert that all authors were returned
        assertEquals(3, uniqueAuthors.size());
        assertEquals(Arrays.asList("Author 1", "Author 2", "Author 3"), uniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsWhenSomeAuthorsAreNotUnique() {
        Book book1 = new Book("Book 1", 5, "Author 1");
        Book book2 = new Book("Book 2", 4, "Author 1");
        Book book3 = new Book("Book 3", 5, "Author 3");

        when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3));

        List<String> uniqueAuthors = manager.uniqueAuthors(fetcher);

        // Assert that only unique authors were returned
        assertEquals(2, uniqueAuthors.size());
        assertEquals(Arrays.asList("Author 1", "Author 3"), uniqueAuthors);
    }

    public void testUniqieAuthorsWhenMultipleAreNotUnique() {
        Book book1 = new Book("Book 1", 5, "Author 1");
        Book book2 = new Book("Book 2", 4, "Author 1");
        Book book3 = new Book("Book 3", 5, "Author 1");
        Book book4 = new Book("Book 4", 5, "Author 2");
        Book book5 = new Book("Book 5", 4, "Author 2");
        Book book6 = new Book("Book 6", 5, "Author 3");

        when(fetcher.all()).thenReturn(Arrays.asList(book1, book2, book3, book4, book5, book6));

        List<String> uniqueAuthors = manager.uniqueAuthors(fetcher);

        // Assert that only unique authors were returned
        assertEquals(3, uniqueAuthors.size());
        assertEquals(Arrays.asList("Author 1", "Author 2", "Author 3"), uniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsWhenThereAreNoBooks() {
        // Set up the fetcher to return no books
        when(fetcher.all()).thenReturn(Collections.emptyList());

        List<String> uniqueAuthors = manager.uniqueAuthors(fetcher);

        // Assert that no authors were returned
        assertEquals(0, uniqueAuthors.size());
    }

    @Test
    public void testUniqueAuthorsWhenOnlyOneBook() {
        Book book1 = new Book("Book 1", 5, "Author 1");

        when(fetcher.all()).thenReturn(Arrays.asList(book1));

        List<String> uniqueAuthors = manager.uniqueAuthors(fetcher);

        // Assert that the author was returned
        assertEquals(1, uniqueAuthors.size());
        assertEquals(Arrays.asList("Author 1"), uniqueAuthors);
    }


}
