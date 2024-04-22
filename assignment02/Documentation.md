# Climbing Stairs:

## Task 1: Code Coverage
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

## Task 2: Designin Contracts
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

## Task 3: Testing Contracts
For this part, I reused the test cases I devised in task 1. However, I changed the test cases for negative integer and 0 to expect an exception as these input values violate pre-conditions. I had 5 test cases in total, all of which passed.

## Test 4: Property Based Testing
I managed to figure out two main properties of the climbing stairs method:
- given a positive integer n, the method must always return result(n-1)+result(n-2), excluding n = {1, 2}
- given a positive integer n, the method must always return a non-negative integer

Accordingly, I created two property test: ```resultEqualsPriorTwo``` and ```resultAlwaysPositive```. I also created two providers, one which provides integers ranged (3, 50) and one which provides integers ranged (1, 70). Originally, I had used 100 as the max n. However, I quickly realized that the result of climbing stair method when n > 91 was way too big of a number for Java's long type. Other than that, no issues were detected during property based testing.

Final line coverage was 76% as some of the if statements implemented for contracts were redundant. See the list below for redundant code:

```
throw new RuntimeException("Input must be an integer");
```
was not read because Java's type safety practices does not allow to pass a non-int to a method that expects an int.

```
throw new RuntimeException("The answer must be non-negative");
```
was not read because the method works in a way that satisfies this post-condition in every tested case.

```
throw new RuntimeException("The answer must be an integer");
```
was not read because the method works in a way that satisfies this post-condition in every tested case.

---

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

---

# Find Duplicate

I started by designing the contracts. Based in the description i came up with the following pre and postconditions:

### Preconditions:

* If the input array has lenght n + 1, it contains only integers between 1 and n (inclusive  )

* This implicitly defines, that at least one number is duplicate, since n integer can exactly contain all unique integers from 1 to n. Size n + 1 will at least contain one duplicate.

* It is assumed that there is only one duplicate number, but it could be repeated more than once. To test this as a precondition, the algorithm to test it would be more comptationally complex than the method itself. Since the description says "Assume", I will just assume that the inputs follow this rule and don't implement this precondition in the code.

This is the implementation of the preconditions, I decided to throw an IllegalArgumentException if they are not met.
```
        // Peconditions: 
        //nums is not null
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        //nums is >= 2
        if (nums.length < 2) {
            throw new IllegalArgumentException("Input array is too short");
        }
        //nums contains only integers in the range [1, n]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums[i] > nums.length - 1) {
                throw new IllegalArgumentException("Input array contains invalid values");
            }
        }
```

### Postconditions

* The output is in the input array.
* The output in is in the input array more than once. 
* The output is between 1 and n.

This is the implementation, I decided a failed postcondition should throw a RuntimeException:

```
        final int duplicate = hare;
        //Postconditions:
        // Check if duplicate is between 1 and n
        if (duplicate < 1 || duplicate > nums.length) {
            throw new RuntimeException("Duplicate value is not between 1 and n");
        }
        // Check if duplicate is in the array
        if (!Arrays.stream(nums).anyMatch(x -> x == duplicate)) {
            throw new RuntimeException("Input array does not contain the duplicate value");
        }
        // Check if duplicate is in the array more than once
        if (Arrays.stream(nums).filter(x -> x == duplicate).count() < 2) {
            throw new RuntimeException("Input array does not contain the duplicate value multiple times");
        }
```

I did not implement any invariants, because the method is static.

### Testing preconditions

I could not test the posconditions, because as long as the method worked as intended, which it did, they would not throw an error. These are the names of the tests. They are self explanatory:

* testFindDuplicate_NullArray_ThrowsIllegalArgumentException()
* testFindDuplicate_ShortArray_ThrowsIllegalArgumentException()
* testFindDuplicate_InvalidValues_ThrowsIllegalArgumentException()
* testFindDuplicate_ValidInput_ReturnsDuplicateValue()

### Property test:

My goal was to capture all properties in one test. My property test `testFindDuplicate_Property()` generates lists of all sizes and with any amount of duplicates, while still staying withing the preconditions.
In the provider, I'm generating a list with size 1 to 20. I then fill it with integers valued index + 1. One of these values is then appended as a duplicate at the end of the list. So if the list was size n, its now the size of n + 1.
Then a random amount of values are converted also to the duplicate (since the duplicate can appear more than twice) and the list is shuffeled again. The list and duplicate value is passed to the property, where the list is converted to an array to be passed to the
tested method.
The provider gives me lists, that statisfy all the properties: Minimum list size 2, contains one number thats duplicate 2 up to list size times, Contains only values from 1 to list size - 1.

### Results

The unit tests aswell as the property test, all passed without me having to fix any bugs in the method.

The tests provided 84% Branch coverage and 86% Construction coverage. A reason is that `public class FindDuplicate {` was not covered, which is expected when testing static methods. Furthermore, as described above, I could not find a way to test my postconditions, which caused the other missed branches and instructions.

For details see the task specific Assets folder.

---

# Longest increasing subsequence

I started by designing the contracts. Based in the description i came up with the following pre and postconditions:

### Preconditions:

* The input array is not null

* The input is an array of integers, possibly containing duplicates.

* Array is not empty (One could look at this as a soft precondition, because in the implementation it returns 0. It could also be seen as part of the program, since a 0 length subsequence is actually correct for a empty input array)

That the input is an array of integer is already enforced by the java type checking. The not null and not empty preconditions were already implemented in the program as soft conditions.

```
// Preconditions:
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
```

### Postconditions

* The longest increasing subsequence can be longer than the input array.

* The longest increasing subsequence should not be shorter than one, excpet whe input array is empty or null.

I decided to throw a RuntimeException in the case a Postcondition isn't met, because a failed postcondition means a bug in the code. This is my implementation of the postconditions:

```
// Postconditions:
if (maxLength > nums.length) {
    throw new RuntimeException("Length of LIS is greater than the number of elements in the array");
}

if (maxLength < 1) {
    throw new RuntimeException("Length of LIS is less than 1 and input array is not empty");
}

```

The method is not defined as static but behaves like one, since there is no state persisted between executions, so there is no need to check invariants in this case.

### Testing preconditions

I could not test the posconditions, because as long as the method worked as intended, which it did, they would not throw an error. These are the names of the tests. They are self explanatory:

* testLengthOfLIS_NullInput_ReturnsZero()
* testLengthOfLIS_EmptyInput_ReturnsZero()
* testLengthOfLIS_ValidInput_ReturnsMaxLength()

### Property Test

My goal was to cover all the properties with one property test. So arrays of length 1 and up with positive, negative and 0 integers. Longest increasing subsequences from length 1 up to length of input array. The distribution of the LIS in the input array is also random. There is also a chance for the input array to contain duplicates. The generator works like this:

Create an array of random length (between 1 and 20 to keep performance an readablility). The array can contain integers from -100000 to 100000 (Again to maintain radability). Every integer in the array is larger than the previous one so at this stage the LIS is equal to array length.
I then change random values in the array to something smaller than their predecessor and with each change the LIS length decreases by one. There is also a chance that a value gets changed to the same value as their predecessor (to generate duplicates) in this case the LIS length also decreases by one. The final edited array and LIS length then get passed to the property test. `testLengthOfLIS_Property()`

### Results

All my test and property variations passed and I did not have to fix a bug in the method.
The LengthOfLIS() method had 86% instruction coverage and 85% branch coverage. The reason I did not have 100% was because the poscondition branches can only be reached if there is a bug in the program, which I could not simulate with my tests.

For details see the task specific Assets folder.

---

# MergeKSortedLists

Note: The documentation does not specify at all what happens when the input is not a sorted linked list. I included a test to assert that a non-sorted input indeed will not be sorted. Otherwise, I left this case open on purpose.
Note: The documentation does not specify what happens if we put in an empty array of list nodes. It is confusing, since Example 2 seems to return an "empty" ListNode, whereas in the code of the provided solution, the first line returns null if the input is empty. I changed the provided solution to return an "empty" ListNode.

## Task 1: Code Coverage
Note: I did initially not test the pre-conditions, since this is part of Task 2 and 3.

I achieved 100% code coverage with the following tests from the test suite:
- T1 - T6 : Check valid inputs for various specifications
- T7 - T9 : Check invalid inputs (not sorted linked lists)

Find a screenshot and the log in the Assets folder, starting with "task1_"

## Task 2: Designing Contracts
Added a JavaDoc for the static method `mergeKLists`.  
I added code directly to the method `mergeKLists` with notes of which contract is implemented.

### Pre-conditions

- Pre1 : The number of all nodes in the input combined is in the range [0,10^4]. For this, I added a helper method in the MergeKSortedLists class `getListNodeCount` which returns the number of elements linked to the linked list.
- Pre2 : Each node value in the input is in the range [-10^4,10^4]. For this, two helper methods `getMinVal` and `getMaxVal` were added.
- Pre3 : The input is not null. Changed in the provided solution. Had to adjust T1_1 to accommodate the newly implemented pre-condition.
- Pre4 : Each linked list of the input is sorted. Had to adjust T3, T7 and T8 to accommodate the newly implemented pre-condition.

### Post-conditions
- Post1 : The output is one single linked list

### Invariants
I can't think of any invariant that should hold, given the short (13 lines) method under test.


## Task 3: Testing Contracts
The following test have been added to test the respective contracts:
- Pre1 : T9 and T10
- Pre2 : T11 - T14

## Task 4: Property-Based Testing
I added a property based test `sameLengthAndSorted` which tests for correct outputs for any kind of combination of valid inputs. Valid inputs (possibly empty) lists of sorted integer lists.

---

# SortedArray2BST

## Task 1: Code Coverage
Note: I did initially not test the pre-conditions, since this is part of Task 2 and 3.

I achieved 100% code coverage with the following tests from the test suite:

T1 : Empty input
T2 : Valid input of length 1
T3 : Valid input of length > 1
Find a screenshot and the log in the Assets folder, starting with "task1_"

## Task 2: Designing Contracts
I added the necessary code directly into the provided solution.  

### Pre-conditions
- PRE1 : The input must contain unique integers
- PRE2 : The length of the input must be between 0 and 10'000
- PRE3 : The input must be sorted ascending
- PRE4 : The input must not be null  

For PRE1 - PRE4, I added code in the ``sortedArrayToBST`` method at the beginning.

### Post-conditions
- POST1 : The output has the same length as the input
- POST2 : The unique set of the output is equal to the unique set of the input  

For POST1 and POST2, I added code at the end of the ``sortedArrayToBST`` method.

### Invariants
- INV1 : After each recursive call of ``constructBSTRecursive``, the `left` attribute must either be null or smaller than the `val` attribute
- INV2 : After each recursive call of ``constructBSTRecursive``, the `right` attribute must either be null or bigger than the `val` attribute

For INV1 and INV2, I added a helper function ``checkInvariant_1_2`` which throws an error if the invariant is violated.

## Task 3: Testing Contracts
The normal working of the function given the added contracts are covered through tests T1 to T3.  
The pre-conditions are tested in the tests T4-T7. No further additions or helpers are needed.
The post-conditions are tested in the test T8.  
The invariants are implicitly tested with those tests T4-T8 and don't need additional explicit testing.

## Task 4: Property-Based Testing
For the property-based tests, I identified the following tests, implemented in the ``PROPERTY-BASED TESTS`` section in the test file:
- Pre-conditions : Test various valid inputs of variable length within the bounds given by PRE2. Make sure they are unique. Verify by checking the post-conditions.
- Pre-conditions : Do the same, but make sure the inputs are not unique and/or sorted and verify that the appropriate exceptions are thrown.

To verify the outcome of all the generated tests, I make use of the post-conditions. If they hold, the result should be what we expect.

---

# Sum of Two Integers

## Code Coverage

Some basic tests were implemented that quickly achieved 100% code coverage.
The code coverage report can be found in root/Assets/htmlReport. An additional screenshot and
the test execution report can be found in the AssetsAss folder.

## Designing Contracts

### Pre-Conditions
The input variables `a` and `b` need to be in the 32-bit signed integer range.
We remark that adding two really large 32-bit signed integers will result in an overflow
when the result is saved in an 32-bit integer. The function does not specify what
happens in that case. Because of how Java works we do not have to test the integers
explicitly since assigned an `int` a value of `3000000000` cannot compile anyway.

### Post-Conditions
The `a` should be the sum of the initial `a` and `b`. The variable `b` should be zero.
Looking at the code we do not have to check the second post condition since the loop
does not stop if it is not true.

### Invariants
No real invariants were identified. Since the function uses a basic type (`int`)
for the input passing it is automatically passing by value. An invariant could
be that `a+b` should stay constant. It is debatable whether this makes sense to test
in a function where a sum should be implemented. We need a `+` operation to test it
and why implement a sum we already have it available for testing reason. The same problem
already emerges when looking at the post condition. Anyway, for the sake of this exercise
we ignore this fact and use `+` for testing reasons.

## Testing Contracts
The only interesting contract to test is that the output should be the sum of the inputs.
As mentioned before we assume that the second will be violated if we choose appropriately
big 32-bit signed integers (overflow).

After testing the overflow scenarios we see that the function works as expected for
overflow. In a real-world scenario a discussion would be necessary with the developers
to see whether this is intended or not. Nevertheless, if it was intended then a better
documentation of the function should be necessary.

Because of the aforementioned points we only write one property that does not take
any boundaries in consideration. This implies that the function should work for overflows
and the property test makes sure it treats overflows the same way as the built-in `+` operator.

In the Assets folder there is a code coverage report after the implemented contracts.
The coverage now is much lower because of branches that cannot be reached. These branches
are not to be tested but are rather there for future code changes. Meaning it is not possible
to trigger them from the outside but rather will be triggered when the code is changed in a 
problematic manner. Some test are also not necessary but are left in the code for the sake
of this exercise. Comments explaining are in the code.

---

# Unique Paths Grid

## Code Coverage

Some basic tests were implemented that quickly achieved 100% code coverage.
The code coverage report can be found in root/Assets/htmlReport. An additional screenshot and
the test execution report can be found in the Assets folder.

## Designing Contracts

### Pre-Conditions
The input variables `m` and `n` need to be in the range one to a hundred.

### Post-Conditions
The output is non-negative and describes the number of possible paths from top-left
to bottom right.

### Invariants
Since the function uses a basic type (`int`) for the input passing it is automatically 
passing by reference. This means that for the outside of the function it really does
not matter whether `m` and `n` change. On the other hand it is essential that they do not
change within the function, so we could check that they are constant within the scope
of the function.

## Testing Contracts
It is necessary to test that the pre-conditions hold and post-conditions hold. It is
pretty straight forward for the boundary testing. The correctness of the function is
a little bit more complicated. We use the fact that the solution depends on the previous
solutions and define our test dependent on other solutions before:

`assertEquals(pathCalc.uniquePaths(m - 1, n) + pathCalc.uniquePaths(m, n - 1) ,
pathCalc.uniquePaths(m, n));`

For the property testing of the input for the `uniquePaths` function we change the function
input type from `int` to `Integer` such that we can use the `@Provide` annotation.

Running the test we realize that some property based tests trigger an assertion.
This is because the number of solutions grows too big too quickly and is way to large
in the edge cases. We need to readjust our `100` boundary assumption or change the return type.
Here we choose to change the return type to `BigInteger` such that it works for inputs
up to a hundred.

After all the adjustments we see that the original function now is covered (root/Assets/htmlReportAfter).
