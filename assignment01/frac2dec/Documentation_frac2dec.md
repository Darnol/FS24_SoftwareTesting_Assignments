# frac2dec

# Specification-based testing

## Step 1 and 2 - Understand the requirements, identify possible ambiguities. Explore some input and outputs
- There is no mention of the numerator being 0. I assume this means the function should return "0" which would make sense.
- There is no mention of NULL values. I don't know how NULL values are supposed to be handled. I assume the function should return NULL if any or both of the inputs are NULL.
- It is not entirely clear to me if the function should return the string "null" or actual null when the denominator is 0. I'll assume it should return the actual null value.

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
- Result is a repeating fraction

## Step 4 - Analyze the boundaries
The first boundary that comes to mind for both of the inputs is the change from negative to positive. So whenever one of the inputs is either -1, 0 or 1.  
The second boundary concerns the output, when it switched from non-repeating to repeating for any combination. This would be something like 1/2, 1/3 and 1/4.

## Step 5 and 6 - Devise test cases and automate
Regarding the null tests, I realized that Java won't let me actually run the function with null as input, since the checker realizes that it is not an int. I can therefore safely discard those tests.  
I try to be parsimounious as the book suggests and not test every combination. For example the 0 input will not be tested with any combination of negative, 0 or positive for the other input, only with a valid one.

## Step 7 - Augment the test suite

## Bugs found and how i solved them
1. testZero -> Function did not return null when denominator=0. Fixed it with an if statement at the beginning.


# Structural testing



# Mutation testing