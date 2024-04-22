package zest;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        // Pre-condition (test could look like that but technically not necessary)
        if (!isValidIntRange(a) || !isValidIntRange(b)) {
            throw new RuntimeException("Input variables must be in signed 32-bit integer range");
        }
        final int res = a + b;

        while (b != 0) {
            int carry = (a & b) << 1;  // Carry now contains common set bits of a and b
            a = a ^ b;  // Sum of bits of a and b where at least one of the bits is not set
            b = carry;  // Carry is shifted by one so that adding it to a gives the required sum

            // Invariant
            assert res == a + b;
        }

        // Post-condition
        assert res == a;
        // assert b == 0; (not necessary since loop runs forever if not true)

        return a;
    }

    // A function like this is always true.. This is why it would not be necessary
    public static boolean isValidIntRange(int num) {
        return num >= Integer.MIN_VALUE && num <= Integer.MAX_VALUE;
    }
}
