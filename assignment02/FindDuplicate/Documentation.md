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

