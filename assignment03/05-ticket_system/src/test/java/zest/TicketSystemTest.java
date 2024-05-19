package zest;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TicketSystemTest {
    private NotificationService notificationService;
    private LogService logService;
    private TicketRepository ticketRepository;
    private TicketManager ticketManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = mock(NotificationService.class);
        logService = mock(LogService.class);
        ticketRepository = mock(TicketRepository.class);

        ticketManager = new TicketManager(notificationService, logService, ticketRepository);
    }

    @Test
    void testCreateTicketsDefault() {
        Ticket ticket = new Ticket("customer@gmail.com", "Issue 1", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);

        // Verify interactions
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketsEmailEmptyString() {
        Ticket ticket = new Ticket("", "Issue 2", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);

        // Verify interactions
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketsIssueEmptyString() {
        Ticket ticket = new Ticket("customer@gmail.com", "", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);

        // Verify interactions
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketsUrgent() {
        Ticket ticket = new Ticket("customer@gmail.com", "Issue 3", TicketPriority.URGENT);
        ticketManager.createTicket(ticket);

        // Verify interactions
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketNotificationServiceError() {
        String notificationError = "Notification service error";
        Ticket ticket = new Ticket("customer@gmail.com", "Issue 4", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);
        doThrow(new RuntimeException(notificationError)).when(notificationService).notifyCustomer(anyString(), anyString());

        // verify that logging and saving still happen ddespite notification service error
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketLogServiceError() {
        String logError = "Log service error";
        Ticket ticket = new Ticket("customer@gmail.com", "Issue 5", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);
        doThrow(new RuntimeException(logError)).when(logService).logTicketCreation(any(Ticket.class));

        // verify that notification and saving still happen
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketNotificationServiceAndLogServiceError() {
        String notificationError = "Notification service error";
        String logError = "Log service error";
        Ticket ticket = new Ticket("customer@gmail.com", "Issue 6", TicketPriority.NORMAL);
        ticketManager.createTicket(ticket);
        doThrow(new RuntimeException(logError)).when(logService).logTicketCreation(any(Ticket.class));
        doThrow(new RuntimeException(notificationError)).when(notificationService).notifyCustomer(anyString(), anyString());

        // Verify that saving still happens
        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
        verify(ticketRepository, times(1)).save(ticket);
    }
}
