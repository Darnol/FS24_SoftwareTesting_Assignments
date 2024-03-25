## Atoi

To understand the requirements the README.md file is thoroughly analyzed. So far no uncertainty or ambiguities in the
specification were identified.
First explore the "normal" behaviour of the program characterised by the simplest cases
described in the README.md under point 4 and 5. Then move on the special inputs as described in 2, more exotic behaviour
as in 3, and edge cases as in point 6. No difference between the specified behaviour and actual behaviour was found.

Now after establishing the context of the function we try to identify the partitions. The ground of the numbers
partitions are the valid numbers:

`-inf < x < -2^31`,
`-2^31 <= x < 0`,
`0`,
`0 < x <= 2^31 - 1`,
`2^31 - 1 < x < inf`

Note that `0` as well as the positive number could also appear with a `+` in front. `0` could also appear with a `-` in
front. Further we partition the valid numbers into the numbers with leading zeros and without leading zeros. The signs
can be partitioned into `-`, `+` and no sign. At last the whole input needs to be partitioned into input with and 
without leading spaces, into input with characters after the first non-digit character and nothing after said
character, and invalid inputs. In the following is a representation of the input where the partitions can be read from:

`<spaces><sign><zeros><number><non-digit char><chars>`

We look at the boundaries for each partition. While trying to encode some partitions, `|` is used to represent 
boundaries. For the number partitions we have the following boundaries:

`-2^31 - 1 | -2^31`,
`-1 | 0`,
`0 | 1`,
`2^31 - 1 | 2^31`

The boundary for an input with leading zeros and no leading zero is:

`0<number> | <number>`

The boundary for the sign partitions is:

`negative numbers | zero | positive numbers`

The boundary for leading spaces partition is:

`<space><non-space char> | <non-space char>`

The boundary for additional characters after the non-digit character is:

`<non-digit char> | <non-digit char><chars>`

The boundaries for the partition of having a digit before a non digit character (invalid inputs) are:

`null | <empty> | <whitespaces><sign> | <non-digit char (excl. ' ' and signs)> | <digit><non-digit char>`

Each partition and its boundaries were tested separately and the corresponding tests are implemented using the JUnit library.
In the end a few partitions were mixed and more creative tests were written. The authors doubts that these tests indeed
cover cases that were not covered before.

Using the Jacoco plugin we saw that the instruction coverage was 96% and the branch coverage was 100%. The instruction
coverage was not 100% because the line specifying the atoi class name was not covered in the tests. This was ignored
because our goal was to test the atoi function properly.

One mutation in the mutation testing was not killed. On line 28:

`            if (num > (Integer.MAX_VALUE - digit) / 10) {`

The change of the conditional test was not detected. Two tests were added that test the number right next to the 
largest integer in the valid not overflowing partitions. Afterward all mutations were killed.

## Combination Sum

To understand the requirements the README.md file is thoroughly analyzed. The fact that the combinations can be returned
in any order seems to give rise to much ambiguity in the behaviour of the function. The last sentence in the description
is also rather confusing. One could ask themselves whether the function makes sure that the combinations do not exceed 
150 or whether for the sake of this exercise we should avoid such complex cases. We interpret it as the latter. 

Compared to the atoi function the requirements of the function are very poorly specified. The description says it should
work for integers, but we immediately realize that the function breaks if negative integers are used as input. For the
further discussion we assume that the requirements intend to work for the integers greater or equal one. We want to 
emphasize again that the function is so poorly specified that most of the inputs become untestable, because we are
lacking context. It is not specified what should happen to empty lists, null inputs and negative numbers. We can only
assume that the function is intended to work for strictly positive numbers.

For the number of elements of the input array we have the following partitions: Empty (unspecified), one element, and 
multiple elements. We can also differ between the input array having duplicates (unspecified) and not having duplicates.
The target value has the following partitions: Non-positive (unspecified) and strictly positive. The output could be
divided into the partitions: No possible combination, single possible combination, and multiple combinations. Additional
partitions for the target can be chosen in combination with the input array: Target smaller than all elements, target 
smaller than some and bigger than some elements, and target larger than all elements combined. Since we are lacking
context for the unspecified partitions we will omit testing them. In a real-world scenario we would contact the
development team for more concise specifications instead of guessing what the function is supposed to do.

Technically, all permutations of solutions are possible solutions since it explicitly says in the requirements that the 
combinations can be in any order. This implies that there are a wide range of equivalence classes regarding the output 
of the function. Technically, we would have to allow our tests to pass if one the possible permutations is returned. For
the sake of simplicity the only canonical solution is tested/allowed. Meaning that smallest elements come first in lists
and lists with smaller elements come first.

Again each partition and its boundaries were tested separately. The tests from the step before are implemented using the JUnit library. In the end a few partitions were mixed and more creative tests were written. The authors doubts that these tests indeed
cover cases that were not covered before.

Using the Jacoco plugin we saw as before that the instruction coverage was not 100% but only because the line declaring 
the class itself was not covered in the tests. This time the branch coverage was not 100%. On line 23:

`} else if (target == 0) {`

One branch was missed. We implement a test case where the target is a negative number to cover the remaining branch. In 
a real-scenario we would of course contact the developer team since this case was never specified. Afterward branch
coverage becomes 100%.

One mutation in the mutation testing was not killed. On line 9:

`Arrays.sort(candidates);`

To kill this mutant we take an already implemented test, copy it, and reverse the order of the candidates. Afterward 
100% of the mutants are killed.


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