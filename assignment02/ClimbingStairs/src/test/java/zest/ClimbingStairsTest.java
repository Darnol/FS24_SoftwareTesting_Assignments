package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

class ClimbingStairsTest {
    private ClimbingStairs cs = new ClimbingStairs();

    @Test()
    @DisplayName("If n is negative, must throw exception")
    void testNegative() {
        assertThrows(RuntimeException.class, () -> {
            cs.climbStairs(-20);
        });
    }

    @Test
    @DisplayName("If n is 0, must throw exception")
    void testZero() {
        assertThrows(RuntimeException.class, () -> {
            cs.climbStairs(0);
        });
    }

/*    @Test
    @DisplayName("If n is not an integer, must throw exception")
    void testNonInteger() {
        assertThrows(RuntimeException.class, () -> {
            cs.climbStairs(12.4);
        });
    }*/

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("If n is 1 or 2, must return n")
    void testOneTwo(int n) {
        assertEquals(n, cs.climbStairs(n));
    }

    @Test
    @DisplayName("If n is 5, must return 8")
    void testGreaterThanTwo() {
        assertEquals(8, cs.climbStairs(5));
    }

    @Property
    void resultEqualsPriorTwo(
            @ForAll("validRange")
            int n) {
        long expected = cs.climbStairs(n - 1) + cs.climbStairs(n - 2);
        long result = cs.climbStairs(n);
        assertEquals(expected, result);
    }

    @Property
    void resultAlwaysPositive(
            @ForAll("positiveValues")
            int n) {
        long result = cs.climbStairs(n);
        assertTrue(result > 0, "The result must always be positive");
    }

    @Provide
    Arbitrary<Integer> validRange() {
        return Arbitraries.integers().between(3, 50); // start at 3 since this hold only for n > 2
    }

    @Provide
    Arbitrary<Integer> positiveValues() {
        return Arbitraries.integers().between(1, 70); // start at 3 since this hold only for n > 2
    }
}


