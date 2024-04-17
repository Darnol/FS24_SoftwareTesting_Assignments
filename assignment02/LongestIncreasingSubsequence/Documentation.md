Constraints

Input:

The input is an array of integers, possibly containing duplicates.

The input array, nums, is not null.

Each element in the input array is an integer that can be positive, negative, or zero.

array is not empty (kind of a constraint, since returning 0 is actually true in this case)

Already existing constraints are soft, since they return 0.

Some constraints are already being enforced by type checking, like that the array contains int.

Output:

output int is <= array length
output is >= 1 (Zeros are already returned at precondition time so dont have to be included here.)

Cant properly test this postcondition, since code doesnt have bug

Ivariant:

The method is not defined as static but behaves like one, since there is no state persisted between executions, so there is no need to check invariants in this case.