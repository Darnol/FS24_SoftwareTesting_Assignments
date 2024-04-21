# Course Schedule:

## Task 1: Code Coverage
I started by reading the specifications of the program and checking out the implementation of the solution. I came up with the test cases below to cover 100% of the lines:
- numCourses is a negative integer
- numCourses is 0
- numCourses is a positive integer
- numCourses is not an integer
- prerequisites is null
- prerequisites is empty
- prerequisites does not have cycle
- prerequisites has cycle
- prerequisites has self dependency
- prerequisites has invalid prerequisite pair

When analyzed with JaCoCo, this test suite provided 100% line coverage. However, the tests for negative integer and 0 cases failed to meet the specifications. As this problem can be handled by contracts that will be implemented in the next step, I did not change the solution code. Also, the test case for non-integer numCourses caused a compile time error because of Java's own type safety practices.

## Task 2: Designin Contracts
Now, we have to determine the pre-conditions, post-conditions and invariants. Course Schedule class has 2 methods and no internal state elements. For the first method canFinish, the inputs are number of courses and prerequisites. This information is enough to create a simple contract for our code.

- pre-conditions:
	- numCourses must be positive
	- numCourses must be an integer
	- prerequisites cannot have [a, a] (no course can require itself directly as a prerequisite)
	- for any prerequisite pair [a, b], 0 ≤ a, b < number of courses
	- prerequisites canno be null
- post-conditions: the method canFinish must return a boolean
- invariants: none

For the implementation of pre-conditions and post-conditions, I opted for if statements instead of assertions. The reason behind this was because although I have control over my own coding environment, I do not have control over the environment in which my solutions could possibly be tested. As assertions can be disabled with a simple JVM parameter, I believed it would be safer to use if statements. I modified the method as follows:

```
public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Check the pre-conditions
	if(!(numCourses > 0)) {
		throw new RuntimeException("numCourses must be positive");
	}

	if(!(numCourses % 1 == 0)) {
		throw new RuntimeException("numCourses must be an integer");
	}

	for (int[] prerequisite : prerequisites) {
		// Check if a course is listed as a prerequisite for itself
		if (prerequisite[0] == prerequisite[1]) {
			throw new RuntimeException("A course cannot be a prerequisite of itself");
		}

		// Check if both course and prerequisite fall within valid range
		if (prerequisite[0] < 0 || prerequisite[0] >= numCourses || prerequisite[1] < 0 || prerequisite[1] >= numCourses) {
			throw new RuntimeException("Invalid prerequisite pair");
		}
	}

        ...
	...
	...
        
    }
```

I did not write contracts for post-conditions as the return values are hardcoded ("return true" or "return false").

## Task 3: Testing Contracts
For this part, I reused the test cases I devised in task 1. However, I changed the test cases for negative integer, 0, null prerequisite, self dependent prerequisite and invalid prerequisite pair to expect an exception as these input values violate pre-conditions. I had 8 test cases in total, all of which passed.

## Test 4: Property Based Testing
I managed to figure out two main properties of the canFinish method:
- if the prerequisites have no cycles, must return true
- if the prerequisites have at least one cycle, must return false

Accordingly, I created two property test: ```returnsTrueIfNoCycles``` and ```returnsFalseIfCycles```. I also created two providers, ```prerequisitesWithNoCycles``` and ```prerequisitesWithCycles```. The first provider randomly takes numbers A ranging from 0 to 24 and numbers B ranging from 25 to 49, creating {A, B} as a prerequisite pair and adding it to the prerequisites array. Because the ranges of A and B never overlap, this ensures there won't be any cyclic prerequisites. The second provider also takes numbers A ranging from 0 to 24 and numbers B ranging from 25 to 49, creating {A, B} as a prerequisite pair and adding it to the prerequisites array. However, it also adds {B, A} to the array, ensuring that cyclic prerequisites will occur. No issues were detected during property based testing.

Final line coverage was 96% as one of the if statements implemented for contracts were redundant:

```
throw new RuntimeException("numCourses must be an integer");
```
was not read because Java's type safety practices does not allow to pass a non-int to a method that expects an int.

