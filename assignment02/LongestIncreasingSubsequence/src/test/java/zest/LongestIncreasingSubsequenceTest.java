package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




public class LongestIncreasingSubsequenceTest {

    @Test
    public void testLongestIncreasingSubsequence() {
        int[] input = {1, 3, 2, 4, 5, 7, 6, 8};
        int expected = 5; // The longest increasing subsequence is {1, 2, 4, 5, 8}
        int actual = new LongestIncreasingSubsequence().lengthOfLIS(input);
        assertEquals(expected, actual);
    }
}