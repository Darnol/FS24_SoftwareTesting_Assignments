# Atoi

## Specification Testing

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

## Structural Testing

Using the Jacoco plugin we saw that the instruction coverage was 96% and the branch coverage was 100%. The instruction
coverage was not 100% because the line specifying the atoi class name was not covered in the tests. This was ignored
because our goal was to test the atoi function properly.

## Mutation Testing

One mutation in the mutation testing was not killed. On line 28:

`            if (num > (Integer.MAX_VALUE - digit) / 10) {`

The change of the conditional test was not detected. Two tests were added that test the number right next to the 
largest integer in the valid not overflowing partitions. Afterward all mutations were killed.

---

# Combination Sum

## Specification Testing

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

## Structural Testing

Using the Jacoco plugin we saw as before that the instruction coverage was not 100% but only because the line declaring 
the class itself was not covered in the tests. This time the branch coverage was not 100%. On line 23:

`} else if (target == 0) {`

One branch was missed. We implement a test case where the target is a negative number to cover the remaining branch. In 
a real-scenario we would of course contact the developer team since this case was never specified. Afterward branch
coverage becomes 100%.

## Mutation Testing

One mutation in the mutation testing was not killed. On line 9:

`Arrays.sort(candidates);`

To kill this mutant we take an already implemented test, copy it, and reverse the order of the candidates. Afterward 
100% of the mutants are killed.

---

# frac2dec

## Specification-based testing

### Step 1 and 2 - Understand the requirements, identify possible ambiguities. Explore some input and outputs
- There is no mention of the numerator being 0. I assume this means the function should return "0" which would make sense.
- There is no mention of NULL values. I don't know how NULL values are supposed to be handled. I assume the function should return NULL if any or both of the inputs are NULL.
- It is not entirely clear to me if the function should return the string "null" or actual null when the denominator is 0. I'll assume it should return the actual null value.
- I don't understand what is supposed to happen when the answer string is actually longer than 104 digits, for example 1/10e105.

### Step 3 - Possible inputs and identification of partitions
We have two int inputs. First I look at them individually. Each of them can be:
- NULL
- 0
- positive
- negative

If I think about them in combination, there are some cases regarding the inputs and outputs that need to be tested:
- One or both NULL
- Both positive
- Numerator positive, denominator negative and vice versa
- Both negative
- Numerator = 0, denominator != 0
- Denominator = 0, numerator != 0
- Denominator = 0, numerator = 0
- Result is an integer > 0
- Result is an integer < 0
- Result is a non-repeating fraction > 0
- Result is a non-repeating fraction < 0
- Result is a repeating fraction > 0 with one digit repeating
- Result is a repeating fraction > 0 with multiple digits repeating
- Result is a repeating fraction < 0 with one digit repeating
- Result is a repeating fraction < 0 with multiple digits repeating 

### Step 4 - Analyze the boundaries
The first boundary that comes to mind for both of the inputs is the change from negative to positive. So whenever one of the inputs is either -1, 0 or 1.  

The second boundary concerns the output, when it switched from non-repeating to repeating for any combination. This would be something like (1,2), (1,3) and (1,4).  

A third boundary regarding the output is when the result changes from integer to fraction. This would be input combinations (1,1), (1,2), (2,2).

### Step 5 and 6 - Devise test cases and automate
Regarding the null tests, I realized that Java won't let me actually run the function with null as input, since the checker realizes that it is not an `int`. I can therefore safely discard those tests.  

I try to be parsimonious as the book suggests and not test every combination. For example the 0 input will not be tested with any combination of negative, 0 or positive for the other input, only with a valid one.

- T1 : One or both inputs are zero
- T2 : Both positive
- T3 : Both negative
- T4 : Pos/neg and vice versa
- T5 : Result is integer > 0
- T6 : Result is integer < 0
- T7 : Result is non-repeating fraction > 1
- T8 : Result is non-repeating fraction between 0 and 1
- T9 : Result is non-repeating fraction < 0
- T10 : Result is repeating fraction > 1
- T11 : Result is repeating fraction between 0 and 1
- T12 : Result is repeating fraction < 0

### Step 7 - Augment the test suite
Here I list tests I actually forgot after looking at my test suite and thinking about some more tests:
- T13 : Both the same, positive and negative and different in both directions

What if a user inputs 01 instead of 1, with trailing zeros? Java will interpret it as an octal number, which is impossible to catch within the function.
- T14 : Octal input, when inputs have leading zeros

### Bugs found and how I solved them
T1 : Function did not return null when `denominator=0`. Fixed it with an if statement at the beginning of the function.


## Structural testing
After running the Specification-based tests, the coverage was 96% line coverage (24/25) and 100% branch coverage. The missing line is there because I never instantiate the class Frac2Dec, since fractionToDecimal is a static method and can be safely ignored. Hence, there was nothing more to cover that was unveiled by looking at the coverage.  

By inspecting the code and running a debugger for a few examples, I could also not think of anything to change about the source code.


## Mutation testing
I ran the Pitest Maven Plugin to generate a report, which created a total of 20 mutants. Of those 20 mutants, 18 were killed. The two surviving mutants concern the line
```Java
res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
```
where the surviving mutant says "changed conditional boundary". This made me realize, that it would be beneficial to test the signs in T2 - T4 with the boundary value 1 and -1 as well. This did, however, not kill the mutants.

---

# generate_parentheses

## Specification-based testing

### Step 1 and 2 - Understand the requirements, identify possible ambiguities. Explore some input and outputs
- The specification does not specify what is supposed to happen if we input a ``null`` value.
- There is a constraint ``1 <= n <= 8``. If also says that if the input is zero or negative, the function will return an empty array. But it does not say what is supposed to happen if an input greater than 8 is used. I'll assume it should return `null`.

### Step 3 - Possible inputs and identification of partitions
We have only one input `n`. I can see the following possible partition inputs:
- ``null``
- zero or negative
- between 1 and 8
- greater than 8

The function is supposed to handle zero or negative inputs differently than greater than zero. So one partition is "the function returns an empty array" vs "the function returns a non-empty array of well-formed parentheses". This is achieved by inputs of zero or negative vs inputs greater than zero smaller or equal to eight.  

Given the constraints, another partition would be "the function returns a non-empty array of well-formed parentheses" vs "function returns ``null``". This is achieved by inputs between one and eight vs inputs greater than eight.

### Step 4 - Analyze the boundaries
For the first partition, the on point is 1, this is where the output changes from empty to non-empty. The off point is therefore 0.  

For the second partition, the on point is 9, this is the first point where the output changes from non-empty to ``null``. The off point in that case is 8.

### Step 5 and 6 - Devise test cases and automate
Regarding the null tests, I realized that Java won't let me actually run the function with null as input, since the checker realizes that it is not an `int`. I can therefore safely discard those tests.

For the valid input between 1 and 8, I'll not test them all, since I can safely assume if the algorithm works for 2, it will also work for 7. I will, however, test the off point of the second partition, which is 8. The tricky part is how to test the result without writing the function anew. After some research, I realized that the number of combinations follows the Catalan Numbers, which is defined as
```
1/(n+1) * binomcoef(2n,n)
```
which would yield 1430 for the case ``n=8``. Hence, I'll check if the function returns a list of this size.

Those are the tests I devised based on the specifications:
- T1 : Input is negative and an off point like -5
- T2 : Input is negative and exactly -1 (closes negative input to off point of first partition)
- T3 : Inputs is zero (off point of first partition)
- T4 : Input is 1 (on point of first partition)
- T5 : Input is 2 (in point of first partition)
- T6 : Input is 8 (off point of second partition)
- T7 : Input is 9 (on point of second partition)
- T8 : Input is 15 (in point of second partition)

### Step 7 - Augment the test suite
After running the tests for the first time, I realized that running the function with inputs greater than 8 runs for a long time. Hence, I realized I caught my first bug, the function does not abort if the input is greater than 8. I fixed the bug, but did not add any more tests.

### Bugs found and how I solved them
T7 : Return ``null`` if the input is greater than 8. Fixed with an additional check at the beginning.

## Structural testing
After running the Specification-based tests, the coverage was 100% line coverage and 100% branch coverage on generateParentheses. There was nothing more to cover that was unveiled by looking at the coverage.  

By inspecting the code and running a debugger for a few examples, I could also not think of anything to change about the source code.

## Mutation testing
I ran the Pitest Maven Plugin to generate a report, which created a total of 23 mutants. Of those 23 mutants, 22 were killed right away. The surviving mutant was
```
public static List<String> generateParentheses(int n) {
    List<String> combinations = new ArrayList();
    if (n<=0) return combinations; // Mutant introduced here
```
and the mutation was "replaced return value with Collections.emptyList". Given that combinations is an empty List, I cannot think of any test that could catch this mutation. I have therefore ignored it.

---

# Maximum Subarray:

## Specification Testing

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
## Structural Testing

The structural testing revealed a 100% line coverage of the method and 93% line coverage of the class. Since it is a static method, the class is not inteded to be initialised, so the missing line:
```public class MaximumSubarray {```
Is not an issue. 
Condition + Branch coverage was 100%.

## Mutation Testing

The Mutation testing with Pitest immediately had the result of 6/6 Mutants killed.
The specification based testing was good enough, such that no missing test cases were revealed with structural and mutation testing.

---

# Median of Arrays

## Structural Testing

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

## Structural Testing

Jacoco gave me a result of 100% line coverage and 100% branch + condition coverage.

## Mutation Testing

The pittest mutation tests killed 6/6 Mutants.

---

# Needle_in_hay:

## Specification-based testing
I started by reading the specifications of the program. I realized the behaviour of the function was not described for cases where only one of the input strings was empty. I decided to make the function return -1 in this case, and added the line below:
```
if (haystack.isEmpty() || needle.isEmpty()) return -1;
```

For the inputs hay and needle, I devised the partitions. Taking into consideration the partitions and the possible boundaries, I ended up with the following test cases:
- Null cases
	- Both needle and hay are null
	- Only needle is null
	- Only hay is null
- Empty string cases
	- Both needle and hay are empty
	- Only needle is empty
	- Only hay is empty
- Needle is not in haystack
	- Only half of the needle is in haystack
	- Needle is shorter than haystack but no part of needle is in haystack
	- Needle is longer than haystack
- Needle is in haystack
	- Needle is in haystack once
	- Needle is in haystack multiple times
	- Needle starts in the first position
	- Needle starts in somewhere in the middle
- Needle, hay or both has special characters (space, +, ? etc.)

In order to keep the number of test cases small, I haven't combined all of these cases. For example, I only tested null cases and empty string cases seperately from the rest. I also did not combine special characters case with any other case.

Results:
- Passed all the tests

## Structural testing
For this step, JaCoCo plugin was used to see the code coverage of the test suite. The structural testing revealed 100% instruction coverage and 100% branch coverage for the method, and 96% instruction coverage and 100% branch coverage for the class. The missing line 
```class NeedleInHay {```
 does not create an issue and can be overlooked.

## Mutation Testing
As the last step, I utilized mutation testing to further enhance my test suite.

NeedleInHay (mutation coverage 95%, 19 mutants killed out of 20):
- CONDITIONALS_BOUNDARY: Conditional boundary in the line ```for(int i = 0; i < (lenHay-lenNed + 1); i++)``` was changed to ```for(int i = 0; i <= (lenHay-lenNed + 1); i++)```, and the test suite did not catch it. This line basically means that we will iterate through the haystack string until the remaining length of haystack is smaller than that of the needle, hence it is impossible for the remaining of the haystack to contain the needle. The mutant does not change the behaviour of our program, only adds a redundant iteration to our for loop. This extra iteration would pose a problem if ```lenHay - lenNed + 1 > lenHay```, because then in the last iteration, the method would try to reach an index that is out of bounds. However, ```lenHay - lenNed + 1 > lenHay``` implies that ```1 > lenNed```, and the method already covers this case from the very beginning. Therefore, I decided not to write a test case to kill this mutant.

---

# Palindrome:

## Specification-based testing
I started by reading the specifications of the program. The program only had one input and one output, an integer and a boolean value respectively. Thinking of the possible values the input could take that would make the program behave differently, I tried to identify the partitions. Originally, I came up with three partitions: negative integers, palindromic positive integers, and non-palindromic positive integers. Although these three partitions felt quite complete, I thought the program might behave differently for varying number of digits. Hence, I divided the positive integer partition into palindromic integers with even number of digits, palindromic integers with odd number of digits, non-palindromic integer with even number of digits, and non-palindromic integer with odd number of digits. I also wanted to try single digit integers, as they should automatically be considered palindromes. Finally, I wanted to the test the program with the minimum and the maximum integer values as they were specified in the constraints. I ended up with the following test cases:
- Negative integers
- Positive integers
	- Palindromic, odd number of digits
	- Palindromic, even number of digits
	- Non-palindromic, odd number of digits
	- Non-palindromic, even number of digits
- Single digits (0... 9)
- Min value -2^20
- Max value 2^20-1

Note: During the process of deciding test cases, I played the devil's advocate and tried to think of ways to break the program. One specific case that came to my mind was integers with leftmost 0's. What would happen if the program allowed users to enter integers manually and a user tried to test the value 010, for example? As the leading 0's don't contribute to the value of the integer and therefore 010 is actually 10, one might expect the program to return "false". On the other hand, another person might think that the backwards of 010 is still 010 and hence the result should be "true". I wrote a test case for this specific situation, and turns out Java considers any integer with leading 0's to be an octal integer. So according to Java, 010 is not decimal 10 but actually decimal 8. Since 8 is a palindrome, when presented with the input 010, the program gave the output "true". Because the specifications did not indicate how the program should behave in this case, and because of Java's own behaviour, I decided not to tackle this problem. However, it is something worth noting.

In total, 8 different test cases were written. The tests were run as parameterized tests with different input values. Results were as given below:
- PalindromeOne passed all the tests
- PalindromeTwo failed the single digits case for 0

Because PalindromeTwo failed the single digits case for 0, I added the line below to the function:
```
if (x == 0) return true
```

## Structural testing
For this step, JaCoCo plugin was used to see the code coverage of both test suites. For PalindromeOne, the structural testing revealed 100% instruction coverage and 100% branch coverage for the method, and 96% instruction coverage and 100% branch coverage for the class. The missing line 
```public class PalindromeOne {```
 does not create an issue and can be overlooked. The same is also true for PalindromeTwo, which has 100% instruction coverage and 100% branch coverage for the method.

During this step, I also looked at the implementation of the classes. While PalindromeOne is quite neat and concise, implementation of PalindromeTwo has quite a few if statements. As a result, I decided to add in and out points of these if conditions to my test parameters.

## Mutation Testing
As the last step, I utilized mutation testing to further enhance my test suites. Unfortunately, neither test suite killed all the mutants.

PalindromeOne (mutation coverage 88%, 7 mutations killed out of 8):
- CONDITIONALS_BOUNDARY: Conditional boundary in the line ```while (start < end)``` was changed to ```while (start <= end)```, and the test suite did not catch it. This change does not affect the behaviour of our method. Actually, the condition could have been written as ```while (start <= end)``` from the get go, and it would not have been wrong. The mutant version simply means that we will be checking if the character in the middle of the integer is equal to itself, which is a redundant step but it will always return true. Thus, the mutant is irrelevant.
  
PalindromeTwo (mutation coverage 81%, 30 mutations killed out of 37):
- CONDITIONALS_BOUNDARY: 2 mutants of this type survived. Neither of them changes the behaviour of our method. These mutants were born of if statements mentioned under the structural testing, for which I had already tested. Hence, no further testing is necessary.
- MATH: 5 mutants of this type survived. For specific input values, these mutants would change the behaviour of our method. I have added a test case to the suite in order to eliminate these mutants. However, this test case was designed specifically to eliminate the MATH mutants created by Pitest. I have learned that Pitest creates MATH mutants always the same way; for example, for every integer multiplication it sees, it creates a mutant by replacing it with integer division. If there were mutants created by replacing integer multiplication with some other operation, this test case might not be able to kill them.
