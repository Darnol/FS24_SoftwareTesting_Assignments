# generate_parentheses

# Specification-based testing

## Step 1 and 2 - Understand the requirements, identify possible ambiguities. Explore some input and outputs
- The specification does not specify what is supposed to happen if we input a ``null`` value.
- There is a constraint ``1 <= n <= 8``. If also says that if the input is zero or negative, the function will return an empty array. But it does not say what is supposed to happen if an input greater than 8 is used. I'll assume it should return `null`.

## Step 3 - Possible inputs and identification of partitions
We have only one input `n`. I can see the following possible partition inputs:
- ``null``
- zero or negative
- between 1 and 8
- greater than 8

The function is supposed to handle zero or negative inputs differently than greater than zero. So one partition is "the function returns an empty array" vs "the function returns a non-empty array of well-formed parentheses". This is achieved by inputs of zero or negative vs inputs greater than zero smaller or equal to eight.  

Given the constraints, another partition would be "the function returns a non-empty array of well-formed parentheses" vs "function returns ``null``". This is achieved by inputs between one and eight vs inputs greater than eight.

## Step 4 - Analyze the boundaries
For the first partition, the on point is 1, this is where the output changes from empty to non-empty. The off point is therefore 0.  

For the second partition, the on point is 9, this is the first point where the output changes from non-empty to ``null``. The off point in that case is 8.

## Step 5 and 6 - Devise test cases and automate
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

## Step 7 - Augment the test suite
After running the tests for the first time, I realized that running the function with inputs greater than 8 runs for a long time. Hence, I realized I caught my first bug, the function does not abort if the input is greater than 8. I fixed the bug, but did not add any more tests.

## Bugs found and how I solved them
T7 : Return ``null`` if the input is greater than 8. Fixed with an additional check at the beginning.

# Structural testing
After running the Specification-based tests, the coverage was 100% line coverage and 100% branch coverage on generateParentheses. There was nothing more to cover that was unveiled by looking at the coverage.  

By inspecting the code and running a debugger for a few examples, I could also not think of anything to change about the source code.

# Mutation testing
I ran the Pitest Maven Plugin to generate a report, which created a total of 23 mutants. Of those 23 mutants, 22 were killed right away. The surviving mutant was
```
public static List<String> generateParentheses(int n) {
    List<String> combinations = new ArrayList();
    if (n<=0) return combinations; // Mutant introduced here
```
and the mutation was "replaced return value with Collections.emptyList". Given that combinations is an empty List, I cannot think of any test that could catch this mutation. I have therefore ignored it.