package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

class SumOfTwoIntegersTest {

    private final SumOfTwoIntegers sumCalc = new SumOfTwoIntegers();

    @Test
    public void simpleAddition() {
        assertEquals(2, sumCalc.getSum(1,1));
        assertEquals(2000, sumCalc.getSum(1000,1000));
        assertEquals(2000000, sumCalc.getSum(1000000,1000000));
        assertEquals(2000000000, sumCalc.getSum(1000000000,1000000000));
    }

    @Test
    public void negativeAddition() {
        assertEquals(0, sumCalc.getSum(-1,1));
        assertEquals(0, sumCalc.getSum(1,-1));
        assertEquals(1, sumCalc.getSum(-1,2));
        assertEquals(1, sumCalc.getSum(2,-1));
        assertEquals(-1, sumCalc.getSum(1,-2));
        assertEquals(-1, sumCalc.getSum(-2,1));
    }

    @Test
    public void zeroAdditions() {
        assertEquals(1, sumCalc.getSum(0,1));
        assertEquals(1, sumCalc.getSum(1,0));
        assertEquals(0, sumCalc.getSum(0,0));
    }

    @Test
    public void overflow() {
        assertEquals(2000000000 + 2000000000 ,sumCalc.getSum(2000000000, 2000000000));
        assertEquals(-2000000000 - 2000000000 ,sumCalc.getSum(-2000000000, -2000000000));
    }

    @Property
    void testGetSum(@ForAll @IntRange(min = Integer.MIN_VALUE, max = Integer.MAX_VALUE) int a,
                    @ForAll @IntRange(min = Integer.MIN_VALUE, max = Integer.MAX_VALUE) int b) {
        assertEquals(a + b, sumCalc.getSum(a, b));
    }
}
