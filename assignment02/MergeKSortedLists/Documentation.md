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