# 04-e_shop

### NOTE
- The task mentions in the description a method `publishOrderPlaced`.  
The class `zest.EventPublisher` does not have a method with this name, but a method named `publishOrderToAllListeners`. I assume that this public method is referred to.
- Task C refers to the `MessageProcessor` class, which is not present in the source code of this exercise. I assume that this is a typo and the `EmailNotificationService` class is meant.
Furthermore, I took the freedom to refactor the project structure, created the package `zest` and moved the classes into it.

### A. Number of invocations
This is tested in the test `test_numberOfInvocations`. I created a real eventPublisher and order and mocked the two dependencies that inherit from the EventListener interface. Then i publish an order and verify that each service calls the method `onOrderPlaced` exactly once.

### B. Content of invocations—ArgumentCaptor
This is tested with `test_ArgumentCaptor` which verifies the content of the arguments.

### C. Content of invocations—Increasing observability
In order to verify the order argument of the function `onOrderPlaced`, I introduced a `Order` return value for the function.
`onOrderPlaced` now returns the input. I had to enhance the tests `test_numberOfInvocations` and `test_ArgumentCaptor` with mocked answers in order to simulate correct return values. Furthermore, I expanded the `EventPublisher` class with two methods `verifyOrderInput` and `isOrderOk` to increase the observability of the class.   

The result of this task are in the two tests `test_enhancedObservability` and `test_enhancedObservability_assertFailure`.


### Comparison
I believe that the `ArgumentCaptor` approach is a more elegant way to verify the arguments in this case. The condition to verify is rather simple and the `ArgumentCaptor` is a good way to do this.  
To enhance the observability of the `EventPublisher` class I had to add quite a lot of code which obfuscates what I'm actually trying to do.