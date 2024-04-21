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