import java.util.List;

public class BookAuthorFetcher {
    private DatabaseConnection dbConnection;

    public BookAuthorFetcher(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Book> all() {
        // This would normally fetch data from a database...The implementation is skipped.
        // ...
        return null;
    }

    public void close() {
        dbConnection.close();
    }
    
}
