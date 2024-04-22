package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.jqwik.api.constraints.IntRange;

import java.math.BigInteger;

class uniquePathsTest {

    private final uniquePaths pathCalc = new uniquePaths();

    @Test
    public void simplePaths() {
        assertEquals(BigInteger.ONE, pathCalc.uniquePaths(1,1));
        assertEquals(BigInteger.valueOf(28), pathCalc.uniquePaths(3,7));
        assertEquals(BigInteger.valueOf(3), pathCalc.uniquePaths(2,3));
        assertEquals(BigInteger.valueOf(6), pathCalc.uniquePaths(3,3));
    }

    @Test
    public void nullPaths() {
        assertThrows(IllegalArgumentException.class, () -> pathCalc.uniquePaths(null, 10));
        assertThrows(IllegalArgumentException.class, () -> pathCalc.uniquePaths(10, null));
    }

    @Property
    void pathsIllegalM(@ForAll("invalid") int m,
                       @ForAll("valid") int n) {
        assertThrows(IllegalArgumentException.class, () -> pathCalc.uniquePaths(m, n));
    }

    @Property
    void pathsIllegalN(@ForAll("valid") int m,
                       @ForAll("invalid") int n) {
        assertThrows(IllegalArgumentException.class, () -> pathCalc.uniquePaths(m, n));
    }

    @Provide
    private Arbitrary<Integer> invalid() {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(0),
                Arbitraries.integers().greaterOrEqual(100 + 1)
        );
    }

    @Provide
    private Arbitrary<Integer> valid() {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(100),
                Arbitraries.integers().greaterOrEqual(1)
        );
    }

    @Test
    public void edgePaths() {
        assertEquals(BigInteger.ONE, pathCalc.uniquePaths(1,1));
        assertEquals(BigInteger.ONE, pathCalc.uniquePaths(1,2));
        assertEquals(BigInteger.ONE, pathCalc.uniquePaths(2,1));
    }


    @Property
    void paths(@ForAll @IntRange(min = 2, max = 100) int m,
               @ForAll @IntRange(min = 2, max = 100) int n) {
        assertEquals(pathCalc.uniquePaths(m - 1, n).add(pathCalc.uniquePaths(m, n - 1)) ,
                pathCalc.uniquePaths(m, n));
    }
}