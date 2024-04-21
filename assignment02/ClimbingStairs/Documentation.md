## Climbing Stairs:

# Task 1: Code Coverage
I started by reading the specifications of the program and checking out the implementation of the solution. I came up with the test cases below to cover 100% of the lines:
- n is a negative integer
- n is 0
- n is a positive integer:
	- n is {1, 2}
	- n is greater than 2
- n is not an integer 

When analyzed with JaCoCo, this test suite provided 100% line coverage. However, as the implementation of the solution only provided the below condition, the tests for negative integer and 0 cases failed to meet the specifications.

```
if (n <= 2) {
            return n;
    }
```

As this problem can be handled by contracts that will be implemented in the next step, I did not change the solution code. Also, the test case for non-integer n caused a compile time error because of Java's own type safety practices.

# Task 2: Designin Contracts
Now, we have to determine the pre-conditions, post-conditions and invariants. Climbing Stairs method takes a single input and returns a single output. In the specifications, it is clearly stated that the input must be a positive integer while the output must be a non-negative integer. We also know that Climbing Stairs class has no internal state elements such as class variables or data structures. This information is enough to create a simple contract for our code.

- pre-conditions:
	- n must be positive
	- n must be integer
- post-conditions:
	- output must be non-negative
	- output must be integer
- invariants: none

For the implementation of pre-conditions and post-conditions, I opted for if statements instead of assertions. The reason behind this was because although I have control over my own coding environment, I do not have control over the environment in which my solutions could possibly be tested. As assertions can be disabled with a simple JVM parameter, I believed it would be safer to use if statements. I modified the method as follows:

```
public long climbStairs(int n) {
        // check the pre-conditions
        if(!(n > 0)) {
            throw new RuntimeException("Input must be positive");
        }

        if(!(n % 1 == 0)) {
            throw new RuntimeException("Input must be an integer");
        }

        ...
	...
	...
        
        // check the post-conditions
        if(!(allWays >= 0)) {
            throw new RuntimeException("The answer must be non-negative");
        }

        if(!(allWays % 1 == 0)) {
            throw new RuntimeException("The answer must be an integer");
        }

        // return if the post-conditions hold
        return allWays;
    }
```

# Task 3: Testing Contracts
For this part, I reused the test cases I devised in task 1. However, I changed the test cases for negative integer and 0 to expect an exception as these input values violate pre-conditions. I had 5 test cases in total, all of which passed.

# Test 4: Property Based Testing
I managed to figure out two main properties of the climbing stairs method:
- given a positive integer n, the method must always return result(n-1)+result(n-2), excluding n = {1, 2}
- given a positive integer n, the method must always return a non-negative integer

Accordingly, I created two property test: ```resultEqualsPriorTwo``` and ```resultAlwaysPositive```. I also created two providers, one which provides integers ranged (3, 50) and one which provides integers ranged (1, 70). Originally, I had used 100 as the max n. However, I quickly realized that the result of climbing stair method when n > 91 was way too big of a number for Java's long type. Other than that, no issues were detected during property based testing.

Final line coverage was 76% as some of the if statements implemented for contracts were redundant. See the list below for redundant code:

```
throw new RuntimeException("Input must be an integer");
``` was not read because Java's type safety practices does not allow to pass a non-int to a method that expects an int.

```
throw new RuntimeException("The answer must be non-negative");
``` was not read because the method works in a way that satisfies this post-condition in every tested case.

```
throw new RuntimeException("The answer must be an integer");
``` was not read because the method works in a way that satisfies this post-condition in every tested case.

