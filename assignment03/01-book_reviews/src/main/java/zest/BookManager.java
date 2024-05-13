import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookManager {
    public List<Book> highRatedBooks(BookRatingsFetcher fetcher) {
        try {
            List<Book> allBooks = fetcher.all();
            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(toList());
        } finally {
            fetcher.close();
        }
    }

    public List<String> uniqueAuthors(BookRatingsFetcher fetcher) {
        try {
            List<Book> allBooks = fetcher.all();
            return allBooks.stream()
                    .map(Book::getAuthor)
                    .distinct()
                    .collect(toList());
        } finally {
            fetcher.close();
        }
    }
}
