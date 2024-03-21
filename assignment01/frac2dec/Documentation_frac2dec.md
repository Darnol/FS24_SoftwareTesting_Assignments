# frac2dec

# Specification-based testing

## Step 1 and 2 - Understand the requirements, identify possible ambiguities. Explore some input and outputs
- There is no mention of the numerator being 0. I assume this means the function should return "0" which would make sense.
- There is no mention of NULL values. I don't know how NULL values are supposed to be handled. I assume the function should return NULL if any or both of the inputs are NULL.
- It is not entirely clear to me if the function should return the string "null" or actual null when the denominator is 0. I'll assume it should return the actual null value.
- I don't understand what is supposed to happen when the answer string is actually longer than 104 digits, for example 1/10e105.

## Step 3 - Possible inputs and identification of partitions
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

## Step 4 - Analyze the boundaries
The first boundary that comes to mind for both of the inputs is the change from negative to positive. So whenever one of the inputs is either -1, 0 or 1.  

The second boundary concerns the output, when it switched from non-repeating to repeating for any combination. This would be something like (1,2), (1,3) and (1,4).  

A third boundary regarding the output is when the result changes from integer to fraction. This would be input combinations (1,1), (1,2), (2,2).

## Step 5 and 6 - Devise test cases and automate
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

## Step 7 - Augment the test suite
Here I list tests I actually forgot after looking at my test suite and thinking about some more tests:
- T13 : Both the same, positive and negative and different in both directions

What if a user inputs 01 instead of 1, with trailing zeros? Java will interpret it as an octal number, which is impossible to catch within the function.
- T14 : Octal input, when inputs have leading zeros

## Bugs found and how i solved them
T1 : Function did not return null when `denominator=0`. Fixed it with an if statement at the beginning of the function.


# Structural testing
After running the Specification-based tests, the coverage was 96% line coverage (24/25) and 100% branch coverage. The missing line is there because I never instantiate the class Frac2Dec, since fracitonToDecimal is a static method and can be safely ignored. Hence, there was nothing more to cover that was unveiled by looking at the coverage.  

By inspecting the code and running a debugger for a few examples, I could also not think of anything to change about the source code.


# Mutation testing
I ran the Pitest Maven Plugin to generate a report, which created a total of 20 mutants. Of those 20 mutants, 18 were killed. The two surviving mutants concern the line
```Java
res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
```
where the surviving mutant says "changed conditional boundary". This made me realize, that it would be beneficial to test the signs in T2 - T4 with the boundary value 1 and -1 as well. This did, however, not kill the mutants.