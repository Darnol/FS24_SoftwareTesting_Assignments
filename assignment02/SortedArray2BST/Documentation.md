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