package zest;

import java.util.Arrays;

public class FindDuplicate {
    public static int findDuplicate(int[] nums) {


        // Peconditions: 
        //nums is not null
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        //nums is >= 2
        if (nums.length < 2) {
            throw new IllegalArgumentException("Input array is too short");
        }
        //nums contains only integers in the range [1, n]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums[i] > nums.length - 1) {
                throw new IllegalArgumentException("Input array contains invalid values");
            }
        }
        int tortoise = nums[0];
        int hare = nums[0];

        // Phase 1: Finding the intersection point of the two runners.  
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];

        } while (tortoise != hare);

        // Phase 2: Finding the "entrance" to the cycle.

        tortoise = nums[0];

        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];

        }
        final int duplicate = hare;
        //Postconditions:
        // Check if duplicate is between 1 and n
        if (duplicate < 1 || duplicate > nums.length) {
            throw new RuntimeException("Duplicate value is not between 1 and n");
        }
        // Check if duplicate is in the array
        if (!Arrays.stream(nums).anyMatch(x -> x == duplicate)) {
            throw new RuntimeException("Input array does not contain the duplicate value");
        }
        // Check if duplicate is in the array more than once
        if (Arrays.stream(nums).filter(x -> x == duplicate).count() < 2) {
            throw new RuntimeException("Input array does not contain the duplicate value multiple times");
        }
        return duplicate;
    }
}
