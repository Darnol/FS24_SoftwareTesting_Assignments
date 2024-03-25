package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NeedleInHayTest {
    @Test
    @DisplayName("NULL values should return -1")
    void testNullBoth() {
        assertEquals(-1, NeedleInHay.find(null, null));
    }

    @Test
    @DisplayName("NULL values should return -1")
    void testNullHay() {
        assertEquals(-1, NeedleInHay.find(null, "a"));
    }

    @Test
    @DisplayName("NULL values should return -1")
    void testNullNeedle() {
        assertEquals(-1, NeedleInHay.find("a", null));
    }

    @Test
    @DisplayName("If both is empty string, must return 0")
    void testEmptyBoth() {
        assertEquals(0, NeedleInHay.find("", ""));
    }

    @Test
    @DisplayName("If needle is empty string, must return -1")
    void testEmptyNeedle() {
        assertEquals(-1, NeedleInHay.find("a", ""));
    }

    @Test
    @DisplayName("If hay is empty string, must return -1")
    void testEmptyHay() {
        assertEquals(-1, NeedleInHay.find("", "a"));
    }

    @Test
    @DisplayName("If needle is not in haystack, must return -1")
    void testNeedleNotInHay() {
        assertEquals(-1, NeedleInHay.find("hakunamatata", "simba"));
    }

    @Test
    @DisplayName("If needle is in haystack once, must return starting index")
    void testNeedleInHayOnce() {
        assertEquals(0, NeedleInHay.find("hakunamatata", "hakuna"));
    }

    @Test
    @DisplayName("If needle is in haystack once, must return starting index")
    void testNeedleInHayOnceMiddle() {
        assertEquals(6, NeedleInHay.find("hakunamatata", "matata"));
    }

    @Test
    @DisplayName("If needle is in haystack multiple times, must return starting index of the first")
    void testNeedleInHayMultiple() {
        assertEquals(0, NeedleInHay.find("hakunamatatahakuna", "hakuna"));
    }

    @Test
    @DisplayName("If only half of needle is in haystack, must return -1")
    void testNeedleInHayHalf() {
        assertEquals(-1, NeedleInHay.find("hakunamatata", "namaste"));
    }

    @Test
    @DisplayName("If haystack is shorter than needle, must return -1")
    void testHayShorterThanNeedle() {
        assertEquals(-1, NeedleInHay.find("haku", "hakuna"));
    }

    @Test
    @DisplayName("If haystack is equal to needle, must return 0")
    void testHayEqualsNeedle() {
        assertEquals(0, NeedleInHay.find("hakunamatata", "hakunamatata"));
    }

    @Test
    @DisplayName("If needle has special characters but is still in hay, must return starting index")
    void testNeedleInHaySpecialChars() {
        assertEquals(8, NeedleInHay.find("hakuna,?- matata*", "- matata*"));
    }
}