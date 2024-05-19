# 03-messages

### First steps
- I refactored the project structure, created the package `zest` and moved the classes into it.
- I inject the dependency `MessageService` of the  `MessageProcessor` class via constructor to be effectively able to mock it.
- For simplicity of comparing ``message`` objects, I added overridden equals and hashCode methods to the `Message` class.

### A. Number of invocations
This is tested in the test `test_numberOfInvocations`. With the mentioned possibility to inject a mock MessageService instance into a real MessageProcessor instance, I can mock the MessageService class and inject it. It is then simple to verify that the sendMessage method is called exactly once.

### B. Content of invocations—ArgumentCaptor
This is tested with `test_ArgumentCaptor`. There I create two captor instances for the two arguments to the sendMessage method. They are used to verify that the passed arguments match the original message.

### C. Content of invocations—Increasing observability
In order to verify the order argument of the function `onOrderPlaced`, I introduced a `Order` return value for the function.
`onOrderPlaced` now returns the input. I had to enhance the tests `test_numberOfInvocations` and `test_ArgumentCaptor` with mocked answers in order to simulate correct return values. Furthermore, I expanded the `EventPublisher` class with two methods `verifyOrderInput` and `isOrderOk` to increase the observability of the class.   

The result of this task are in the two tests `test_enhancedObservability` and `test_enhancedObservability_assertFailure`.


### Comparison
I believe that the `ArgumentCaptor` approach is a more elegant way to verify the arguments in this case. The condition to verify is rather simple and the `ArgumentCaptor` is a good way to do this.  
To enhance the observability of the `EventPublisher` class I had to add quite a lot of code which obfuscates what I'm actually trying to do.  

For test automation, I decided to use jqwik again and generate some Order instances which are then run in tests for both approaches. In terms of automation ease, there is not really a difference between the two approaches once the enhanced observability extra code is written. You can find the tests in the `EventPublisherTest_TaskD` test suite.