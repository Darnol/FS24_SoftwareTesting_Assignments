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
        //Postconditions:
        // Check if hare is not in the array
        if (hare < 1 || hare > nums.length) {
            throw new RuntimeException("Input array does not contain the duplicate value");
        }
        return hare;
    }
}
