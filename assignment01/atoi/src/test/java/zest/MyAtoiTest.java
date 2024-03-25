package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyAtoiTest {
    @Test
    public void numberPartitions() {
        // -inf < x < -2^31
        assertEquals(-2147483648, MyAtoi.myAtoi("-3000000000"));
        assertEquals(-2147483648, MyAtoi.myAtoi("-2147483649"));
        // -2^31 <= x < 0
        assertEquals(-2147483648, MyAtoi.myAtoi("-2147483648"));
        assertEquals(-2147483647, MyAtoi.myAtoi("-2147483647"));
        assertEquals(-1000000000, MyAtoi.myAtoi("-1000000000"));
        assertEquals(-1, MyAtoi.myAtoi("-1"));
        // 0
        assertEquals(0, MyAtoi.myAtoi("0"));
        // 0 < x <= 2^31 - 1
        assertEquals(1, MyAtoi.myAtoi("1"));
        assertEquals(1000000000, MyAtoi.myAtoi("1000000000"));
        assertEquals(2147483646, MyAtoi.myAtoi("2147483646"));
        assertEquals(2147483647, MyAtoi.myAtoi("2147483647"));
        // 2^31 - 1 < x < inf
        assertEquals(2147483647, MyAtoi.myAtoi("2147483648"));
        assertEquals(2147483647, MyAtoi.myAtoi("3000000000"));
    }

    @Test
    public void leadingZeros() {
        assertEquals(1, MyAtoi.myAtoi("000001"));
        assertEquals(1, MyAtoi.myAtoi("01"));
        assertEquals(0, MyAtoi.myAtoi("000000"));
    }

    @Test
    public void signs() {
        assertEquals(0, MyAtoi.myAtoi("-0"));
        assertEquals(0, MyAtoi.myAtoi("+0"));
        assertEquals(10, MyAtoi.myAtoi("+10"));
    }

    @Test
    public void leadingSpaces() {
        assertEquals(1, MyAtoi.myAtoi("      1"));
        assertEquals(1, MyAtoi.myAtoi(" 1"));
    }

    @Test
    public void trailingCharacters() {
        assertEquals(1, MyAtoi.myAtoi("1w"));
        assertEquals(1, MyAtoi.myAtoi("1wwwwwww"));
    }

    @Test
    public void invalidInputs() {
        assertEquals(0, MyAtoi.myAtoi(null));
        assertEquals(0, MyAtoi.myAtoi(""));
        assertEquals(0, MyAtoi.myAtoi(" "));
        assertEquals(0, MyAtoi.myAtoi(" -"));
        assertEquals(0, MyAtoi.myAtoi("w"));
        assertEquals(0, MyAtoi.myAtoi("-w"));
    }

    @Test
    public void creativeInputs() {
        assertEquals(0, MyAtoi.myAtoi("--10"));
        assertEquals(0, MyAtoi.myAtoi("word10"));
        assertEquals(1, MyAtoi.myAtoi("  +1 0"));
        assertEquals(0, MyAtoi.myAtoi("  +   8"));
        assertEquals(-3, MyAtoi.myAtoi("  -3who are you 99 people??"));
        assertEquals(2147483647, MyAtoi.myAtoi("999999999999999999999999999999999999999999999999999999999"));
    }
}