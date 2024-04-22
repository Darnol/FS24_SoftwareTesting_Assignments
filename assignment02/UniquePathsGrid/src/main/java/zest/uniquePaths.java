package zest;

import java.math.BigInteger;

public class uniquePaths {
    public BigInteger uniquePaths(Integer m, Integer n) {
        // Pre-condition
        if (m == null || n == null) {
            throw new IllegalArgumentException("Input variables cannot be null");
        }
        if (!isValidRange(m) || !isValidRange(n)) {
            throw new IllegalArgumentException("Input variables must be in range (1, 100)");
        }

        final int init_m = m;
        final int init_n = n;

        BigInteger[][] dp = new BigInteger[m][n];

        // Initialize the first row and column to 1 since there's only one way to reach any cell in the first row or column
        for (int i = 0; i < m; i++) {
            dp[i][0] = BigInteger.valueOf(1);
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = BigInteger.valueOf(1);
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j].add(dp[i][j - 1]); // The number of paths to the current cell is the sum of the paths to the cell above and to the left
            }
        }

        // Invariant
        assert init_m == m;
        assert init_n == n;

        // Post-condition
        assert dp[m - 1][n - 1].compareTo(BigInteger.ZERO) >= 0;

        return dp[m - 1][n - 1]; // The bottom-right cell contains the total number of unique paths
    }

    public static boolean isValidRange(int num) {
        return num >= 1 && num <= 100;
    }

}
