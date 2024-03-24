package zest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeOneTest {
    @Test
    void testIsPalindromeForNonPalindrome() {
        int x = 12345; // Example non-palindrome number
        assertFalse(PalindromeOne.isPalindrome(x));
    }
    @ParameterizedTest
    @ValueSource(ints = {-1, -121, -11, -12, -122})
    @DisplayName("Negative integers must be dubbed non-palindromes")
    void testNegativeInteger(int number) {
        assertFalse(PalindromeOne.isPalindrome(number),
                "Negative integers should be dubbed non-palindromes");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Single digits must be dubbed palindromes")
    void testSingleDigits(int number) {
        assertTrue(PalindromeOne.isPalindrome(number),
                "Single digits must be dubbed palindromes");
    }

    @ParameterizedTest
    @ValueSource(ints = {111, 222, 141, 21912, 3139313})
    @DisplayName("Palindromes with odd number of digits must be dubbed palindromes")
    void testOddPalindrome(int number) {
        assertTrue(PalindromeOne.isPalindrome(number),
                "Palindromes with odd number of digits must be dubbed palindromes");
    }

    @ParameterizedTest
    @ValueSource(ints = {112, 722, 144, 21922, 12345, 7139313})
    @DisplayName("Non-palindromes with odd number of digits must be dubbed non-palindromes")
    void testOddNonPalindrome(int number) {
        assertFalse(PalindromeOne.isPalindrome(number),
                "Non-palindromes with odd number of digits must be dubbed non-palindromes");
    }

    @ParameterizedTest
    @ValueSource(ints = {22, 44, 1221, 7777, 913319, 99999999})
    @DisplayName("Palindromes with even number of digits must be dubbed palindromes")
    void testEvenPalindrome(int number) {
        assertTrue(PalindromeOne.isPalindrome(number),
                "Palindromes with even number of digits must be dubbed palindromes");
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 42, 3221, 7977, 914319, 99999990})
    @DisplayName("Non-palindromes with even number of digits must be dubbed non-palindromes")
    void testEvenNonPalindrome(int number) {
        assertFalse(PalindromeOne.isPalindrome(number),
                "Non-palindromes with even number of digits must be dubbed non-palindromes");
    }

    @Test
    @DisplayName("Max val must be dubbed non-palindrome")
    void testMaxVal() {
        assertFalse(PalindromeOne.isPalindrome((int)(Math.pow(2, 20)-1)),
                "Max val must be dubbed non-palindrome");
    }

    @Test
    @DisplayName("Min val must be dubbed non-palindrome")
    void testMinVal() {
        assertFalse(PalindromeOne.isPalindrome(-(int)(Math.pow(2, 20)-1)),
                "Min val must be dubbed non-palindrome");
    }
}