# MergeKSortedLists

## Task 1: Code Coverage
Note: I did initially not test the pre-conditions, since this is part of Task 2 and 3.
Note: The documentation does not specify at all what happens when the input is not a sorted linked list. I included a test to assert that a non-sorted input indeed will not be sorted. Otherwise I left this case open on purpose.

I achieved 100% code coverage with the following tests from the test suite:
- T1 - T6 : Check valid inputs for various specifications
- T7 - T9 : Check invalid inputs (not sorted linked lists)

Find a screenshot and the log in the Assets folder, starting with "task1_"

## Task 2: Designing Contracts
Added a JavaDoc for the static method `mergeKLists` 

### Pre-conditions

- Pre1 : The number of all nodes in the input combined is in the range [0,10^4]
- Pre2 : Each node value in the input is in the range [-10^4,10^4]
- Pre3 : The input is not null
- Pre4 : Each linked list of the input is sorted

### Post-conditions
- Post1 : The output is one single linked list

### Invariants



## Task 3: Testing Contracts

## Task 4: Property-Based Testing