package zest;

public class ClimbingStairs {
    public long climbStairs(int n) {
        // check the pre-conditions
        if(!(n > 0)) {
            throw new RuntimeException("Input must be positive");
        }

        if(!(n % 1 == 0)) {
            throw new RuntimeException("Input must be an integer");
        }

        // continue if the pre-conditions hold
        if (n <= 2) {
            return n;
        }
        long oneStepBefore = 2;
        long twoStepsBefore = 1;
        long allWays = 0;

        for (int i = 2; i < n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
        }

        // check the post-conditions
        if(!(allWays >= 0)) {
            throw new RuntimeException("The answer must be non-negative");
        }

        if(!(allWays % 1 == 0)) {
            throw new RuntimeException("The answer must be an integer");
        }

        // return if the post-conditions hold
        return allWays;
    }
}
