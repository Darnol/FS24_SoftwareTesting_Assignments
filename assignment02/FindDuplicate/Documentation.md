#Find Duplicate

Designing contracts

Input:
An array **nums** containing n + 1 integers where each integer is between 1 and n.
This implicitly defines, that at least one number is duplicate, since n integer can exactly
containe all unique integers from 1 to n. Size n + 1 will at least contain one duplicate.

n >= 1 So array is of at least size 2 to ensure that there is a duplicate.

Assume that there is only one duplicate number, but it could be repeated more than once. Since it says assume, I will write it here 

If list contains ints that are not between 1 and n, or size of array is <2 or array is null, IllegalArgumentException will be thrown.

Output:
The output is in the array. The output is an int(Couldnt write test for that so took it out). The output in is in the array more than once. The output in is between 1 and n.

Invariants:
No invariants. The method is static

Property test:
In the provider, I'm generating a list with size 1 to 20. I then fill it with integers valued index + 1. One of these values is then appended as a duplicate at the end of the list. So if the list was size one, its now the minimum of 2.
Then a random amount of values are converted also to the duplicate and the list is shuffeled again. The list and duplicate value is passed to the property, where the list is converted to an array to be passed to the
tested method.
The provider gives me lists, that statisfy all the properties: Minimum list size 2, contains one number thats duplicate 2 up to list size times, Contains only values from 1 to list size -1.

The unit tests aswell as the property test, all passed without having to fix any bugs in the method.

The tests provided 100% Branch coverage and 92% line coverage. Only 'public class FindDuplicate {' was not covered, which is expected with static methods.

