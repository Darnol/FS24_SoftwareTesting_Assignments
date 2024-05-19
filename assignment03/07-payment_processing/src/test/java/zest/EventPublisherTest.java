package zest;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;


public class EventPublisherTest {
    private EventPublisher eventPublisher;
    private TransactionService transactionService;
    private FraudDetectionService fraudDetectionService;
    private PaymentProcessor paymentProcessor;


    List<AuditService> MockAndSubscribeAuditServices(int nrServices)
    {
        List<AuditService> auditServices = new ArrayList<>();
        for (int i = 0; i < nrServices; i++) {
            AuditService auditService = mock(AuditService.class); // Assuming a default constructor exists
            eventPublisher.subscribe(auditService);
            auditServices.add(auditService);
        }
        return auditServices;
    }

    List<Transaction> CreateTransactions(int nrTransactions, boolean isFraud)
    {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < nrTransactions; i++) {
            Transaction transaction = mock(Transaction.class); // Assuming a default constructor exists
            when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(!isFraud);
            transactions.add(transaction);
        }
        return transactions;
    }

    @BeforeEach
    void SetUp() {
        eventPublisher = new EventPublisher();
        transactionService = mock(TransactionService.class);
        fraudDetectionService = mock(FraudDetectionService.class);
        paymentProcessor = new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);
    }

    @Test
    void TestSingleAuditSingleTransactionComplete() {
        AuditService auditService1 = mock(AuditService.class);
        eventPublisher.subscribe(auditService1);

        Transaction transaction = mock(Transaction.class);
        when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(true);

        paymentProcessor.processPayment(transaction);

        verify(auditService1, times(1)).onTransactionComplete(transaction);
    }

    @Test
    void TestSingleAuditSingleTransactionFail() {
        AuditService auditService1 = mock(AuditService.class);
        eventPublisher.subscribe(auditService1);

        Transaction transaction = mock(Transaction.class);
        when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(false);

        paymentProcessor.processPayment(transaction);

        verify(auditService1, times(0)).onTransactionComplete(transaction);
    }

    @Test
    void TestMultipleAuditSingleTransactionComplete() {
        var auditServices = MockAndSubscribeAuditServices(3);

        Transaction transaction = mock(Transaction.class);
        when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(true);

        paymentProcessor.processPayment(transaction);

        for (var auditService : auditServices)
        {
            verify(auditService, times(1)).onTransactionComplete(transaction);
        }
    }

    @Test
    void TestSingleAuditMultipleTransactionComplete() {
        AuditService auditService1 = mock(AuditService.class);
        eventPublisher.subscribe(auditService1);

        var transactions = CreateTransactions(3, false);

        for (var transaction : transactions)
        {
            paymentProcessor.processPayment(transaction);
        }

        verify(auditService1, times(3)).onTransactionComplete(any(Transaction.class));
    }

    @Test
    void TestTransactionIntegrity() {
        AuditService auditService1 = mock(AuditService.class);
        eventPublisher.subscribe(auditService1);

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        Transaction transaction = mock(Transaction.class);
        when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(true);

        paymentProcessor.processPayment(transaction);

        verify(auditService1, times(1)).onTransactionComplete(transactionCaptor.capture());
        assertEquals(transaction, transactionCaptor.getAllValues().get(0));
    }

    @Test
    void TestTransactionIntegrityObservability() {
        AuditService auditService1 = mock(AuditService.class);
        eventPublisher.subscribe(auditService1);

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        Transaction transaction = mock(Transaction.class);
        when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(true);

        var transactionNew = paymentProcessor.processPayment(transaction);

        assertEquals(transaction, transactionNew);
    }
}
