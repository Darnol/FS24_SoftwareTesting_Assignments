# Payment Processing System

## A. Number of Invocations
To test the number of invocations we first write a setup method that mocks the
transaction and fraud service and initializes the event publisher and payment 
processor accordingly. The first simple test we write are tests where we mock
an audit service and a transaction. We say one time the transaction should be
detected as a fraud and one time not. Next we create helper functions that mock
audit services and transactions and subscribe them respectively tell the fraud 
detection service whether they should be seen as a fraud or not. These functions
help to implement two other test cases for multiple audit services resp. multiple
transactions. Furthermore, these functions help developers in the future to write
easier tests.

## B. Content of invocations — ArgumentCaptor
To test the transaction integrity we copy our first simple test and add an 
argument captor to capture the transaction argument. Afterward, we can compare
whether the captured argument was the same argument as given to the payment
service.

## C. Content of invocations — Increasing observability
To enhance the observability we simply change the payment processing function of
the payment processor and the publish function of the event publisher 
to return the transaction object in the end. This gives us an option to see
whether the (right?) transaction was really passed to the audit service.

## D. Comparison
B
- Advantages
  - To go method if observability is not given
  - Elegant way to capture what is put into the functions
- Disadvantages
  - An additional framework is needed
  - More complex test code

D
- Advantages
  - Direct access to data
  - Simple test code
- Disadvantages
  - May conflict the initial code design

Me personally, I would use the observability method only if it does not conflict
the original design of the code. If I test my own code then I would do a double
take why I made the design decisions in the first place and whether enhancing
observability is really not an option. If I am testing someone else's code, I 
would primarily use an argument captor. Only if it is really necessary I would
discuss with the other developer whether her design should be changed. 