# Sum of Two Integers

## Code Coverage

Some basic tests were implemented that quickly achieved 100% code coverage.
The code coverage report can be found in root/Assets/htmlReport. An additional screenshot and
the test execution report can be found in the Assets folder.

## Designing Contracts

### Pre-Conditions
The input variables `m` and `n` need to be in the range one to a hundred.

### Post-Conditions
The output is non-negative and describes the number of possible paths from top-left
to bottom right.

### Invariants
Since the function uses a basic type (`int`) for the input passing it is automatically 
passing by reference. This means that for the outside of the function it really does
not matter whether `m` and `n` change. On the other hand it is essential that they do not
change within the function, so we could check that they are constant within the scope
of the function.

## Testing Contracts
It is necessary to test that the pre-conditions hold and post-conditions hold. It is
pretty straight forward for the boundary testing. The correctness of the function is
a little bit more complicated. We use the fact that the solution depends on the previous
solutions and define our test dependent on other solutions before:

`assertEquals(pathCalc.uniquePaths(m - 1, n) + pathCalc.uniquePaths(m, n - 1) ,
pathCalc.uniquePaths(m, n));`

For the property testing of the input for the `uniquePaths` function we change the function
input type from `int` to `Integer` such that we can use the `@Provide` annotation.

Running the test we realize that some property based tests trigger an assertion.
This is because the number of solutions grows too big too quickly and is way to large
in the edge cases. We need to readjust our `100` boundary assumption or change the return type.
Here we choose to change the return type to `BigInteger` such that it works for inputs
up to a hundred.

After all the adjustments we see that the original function now is covered (root/Assets/htmlReportAfter).