package zest;

import javax.management.RuntimeErrorException;

public class LongestIncreasingSubsequence {
    // Preconditions:
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1; // Each element is an increasing subsequence of length 1

            // Check all previous elements to find longer increasing subsequences
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }
        // Postconditions:
        if (maxLength > nums.length || maxLength < 1) {
            throw new RuntimeException("Length of LIS is greater than the number of elements in the array");
        }

        return maxLength;
    }
}

