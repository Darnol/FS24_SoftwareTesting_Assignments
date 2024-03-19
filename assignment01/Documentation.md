## Maximum Subarray:

I started by reading the specifications. I realized, there was no description of the behaviour if null was passed to the function, so I decided to make the function return 0 in that case. I then started to construct categories and partitions. In the book, there was mostly one category per input parameter. Since this function had only one input parameter, I went straight onto partitions:


Null

Negative numbers (Variations: All negative, Negative and positive)

Input array length (Variatins: empty, 1, >1)

Subarray length (Variatins: empty, 1, >1)

Subarray Position (Variations: Beginning, Middle, End)

Zeros (Variations: Leading, Trailing, Both)

Multiple largest subarrays

To decrease The number of tests, Special cases Null, empty and array with length one are only tested once. For the other partitions, I did some overlapping thests where I saw it but other than that tested them all in combination with each other. I probably ended up writing a few non-useful tests, but the effort of weeding these out would have exceeded the effort of just writing a few more. I ended up catching one bug with testMaxSubArrayEmpty(), where the empty array case was not defined. I added this code to the function:

```
if (a == null){
        return 0;
    }

if (a.length == 0) {
        return 0;
}
```
The structural testing revealed a 100% line coverage of the method and 93% line coverage of the class. Since it is a static method, the class is not inteded to be initialised, so the missing line:
```public class MaximumSubarray {```
Is not an issue. 
Condition + Branch coverage was 100%.

The Mutation testing with Pitest immediately had the result of 6/6 Mutants killed.
The specification based testing was good enough, such that no missing test cases were revealed with structural and mutation testing.


## Median of Arrays

I started by reading the specifications. I realized, there was a description missing for when both input arrays were empty, but from reding the code I saw, this would return -1, so I assumed this was inteded. I then started thinking about categories and saw that the two input parameters have essentially the same partitions. So my approach mostly was to think of partitions for a parameter and then use the same partitions for the second parameter in different combinations.
I started with special cases.

Array 1 or/and Array 2 null

Array 1 or/and Array 2 not sorted

Array 1 or/and Array 2 empty

I then came up with different partitions:

Even/Uneven number of total elements. (because median works differently)

Array length (Variation: Empty, one, >one)

Negative numbers (Variation: Only negative, mixed)

Order between Array (All numbers in one array bigger than the other, order mixed between arrays)

Arrays contain Same number (Variations: All numbers the same, subarray of numbers is the same)

Edge case negative numbers: Also try with zeros

I did the special cases separately and try to overlap some of the other partitions to decrease the number of tests. While doing the tests I was also switching around array 1 and array 2, because even though they have the same partitions, the algorithm might treat them differently.
With testFindMedianSortedArraysAllSameNumber() I found, that the same number multiple times in the same array, would return 0. Although the specifications are a bit ambiguous here, it made sense to me that this should actually not return 0 by default, so a changed a line in the method isArraySortedAscending() to:
```
if (array[i] > array[i + 1]) {
```
Because I used the same instantiation of the MedianOfArrays class for multiple tests, p1 and p2 would still have values from the previous test and thus cause my tests to fail. I figured this could also lead to issues when using objects of this class in the code, so I added a reset of these values whenever findMedianSortedArrays() is called:
```
p1 = 0; p2 = 0;
```

Jacoco gave me a result of 100% line coverage and 100% branch + condition coverage.
The pittest mutation tests killed 6/6 Mutants.