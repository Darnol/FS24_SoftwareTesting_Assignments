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
In order to verify the arguments of the function `processMessages`, I introduced a private function `verifyMessages` in the `MessageProcessor` class which compares the input to the `processMessages` to the element that were actually put into the `sendMessage` function. The class then contains a boolean field which contains true if the content was verified correctly.  
The resulting test is `test_increasingObservability`.



### Comparison
In this exercise, I liked the way of increasing the observability more instead of the Argument Captor approach. It felt to me very natural to extend the `MessageProcessor` with a verification function, and it did not generate a lot of new code. The test
then definitely looks simpler than the Argument Captor approach which has to introduce those new captor classes.  

For test automation, I decided to use jqwik again and generate some Message instances which are then run in tests for both approaches. Those new tests are moved to a separate file `MessageProcessorTest_TaskD`. There are no specifications in the description for the `Message` class that help me figure out any edge cases or conditions that need special attention. Hence, I generated some inputs, but only with valid string instances for all the fields of the `Message` class.