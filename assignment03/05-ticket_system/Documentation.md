# Ticket System:

### Unit Tests for the createTicket Method
`createTicket()` method of TicketManager class takes a ticket object, logs the ticket creation action via LogService, sends a notification to the customer via NotificationService, and saves the ticket to the database via TicketRepository. The ticket object needs a customer email, an issue description and ticket priority (normal or urgent). NotificationService, LogService and TicketRepository are injected into the TicketManager class and can be mocked. However, as mocking has its disadvantages, it is important to choose when and what to mock.

- NotificationService: This service is responsible for sending notifications to customers. It's an external dependency, and its behavior and state should not impact the tests for TicketManager. Mocking this service allows us to simulate notifications without actually sending them.
- LogService: This service handles logging actions. Similar to NotificationService, it is an external dependency that does not directly influence the core functionality of ticket management. Mocking it allows us to verify that logging occurs without generating actual logs.
- TicketRepository: This service involves data storage and retrieval, hence it's another external dependency. Mocking it allows us to simulate saving tickets without interacting with a real database.

1. All of these services deal with external dependencies which should not have an impact on the logic of `createTicket()` method. By mocking these dependencies, we can ensure that the tests focus solely on the logic within the TicketManager class and prevent the side effects of failure or issues of external systems. Mocking these dependencies also allows us to control their behavior and simulate cases that are difficult to control, such as failures in log service or errors in notification service. On top of that, we do not have to use network calls or database operations during testing, which means our tests will run faster.

Because of these points, test cases were implemented with Java's Mockito framework. 4 test cases were written: a default test case with valid values, 2 test cases with empty strings, and a test case with an urgent ticket.

2. Although mocking provides ample advantages, it also has its drawbacks. For example, if behavior changes that would cause the ticket system to fail were to occur in any of the external dependencies, our tests would still pass as we use mocks. This means we need to make sure our system works correctly by running integration tests. Of course there are other drawbacks of using mocks, but in our case, this seems to be the most prevalent one.

3. As per assignment directions, 3 test cases with simulated failures in external dependencies were added to the test suite to ensure the behavior of the `createTicket()` method under these circumstances. The results were as expected, and the tests all passed. Results of the tests can be found in the Assets folder.

## Some Notes
I changed the project structure, created `zest` package, and created a separate folder for tests.

Tests were written in a manner that prevents flakiness as much as possible. A `setUp()` method was used to ensure each test would have clean, reliable resources to use. The test suite is fast and the tests are cohesive, independent and isolated.
