# Sum of Two Integers

## Code Coverage

Some basic tests were implemented that quickly achieved 100% code coverage.
The code coverage report can be found in root/Assets/htmlReport. An additional screenshot and
the test execution report can be found in the AssetsAss folder.

## Designing Contracts

### Pre-Conditions
The input variables `a` and `b` need to be in the 32-bit signed integer range.
We remark that adding two really large 32-bit signed integers will result in an overflow
when the result is saved in an 32-bit integer. The function does not specify what
happens in that case. Because of how Java works we do not have to test the integers
explicitly since assigned an `int` a value of `3000000000` cannot compile anyway.

### Post-Conditions
The `a` should be the sum of the initial `a` and `b`. The variable `b` should be zero.
Looking at the code we do not have to check the second post condition since the loop
does not stop if it is not true.

### Invariants
No real invariants were identified. Since the function uses a basic type (`int`)
for the input passing it is automatically passing by reference. An invariant could
be that `a+b` should stay constant. It is debatable whether this makes sense to test
in a function where a sum should be implemented. We need a `+` operation to test it
and why implement a sum we already have it available for testing reason. The same problem
already emerges when looking at the post condition. Anyway, for the sake of this exercise
we ignore this fact and use `+` for testing reasons.

## Testing Contracts
The only interesting contract to test is that the output should be the sum of the inputs.
As mentioned before we assume that the second will be violated if we choose appropriately
big 32-bit signed integers (overflow).

After testing the overflow scenarios we see that the function works as expected for
overflow. In a real-world scenario a discussion would be necessary with the developers
to see whether this is intended or not. Nevertheless, if it was intended then a better
documentation of the function should be necessary.

Because of the aforementioned points we only write one property that does not take
any boundaries in consideration. This implies that the function should work for overflows
and the property test makes sure it treats overflows the same way as the built-in `+` operator.

