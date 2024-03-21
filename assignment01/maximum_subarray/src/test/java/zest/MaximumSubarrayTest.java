package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MaximumSubarrayTest {

    @Test
    void testMaxSubArray() {
        // Test case 1: Empty array
        int[] arr1 = {};
        assertEquals(0, MaximumSubarray.maxSubArray(arr1));

        // Test case 2: Array with one element
        int[] arr2 = {5};
        assertEquals(5, MaximumSubarray.maxSubArray(arr2));

        // Test case 3: Array with positive numbers
        int[] arr3 = {1, 2, 3, 4, 5};
        assertEquals(15, MaximumSubarray.maxSubArray(arr3));

        // Test case 4: Array with negative numbers
        int[] arr4 = {-1, -2, -3, -4, -5};
        assertEquals(-1, MaximumSubarray.maxSubArray(arr4));

        // Test case 5: Array with both positive and negative numbers
        int[] arr5 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, MaximumSubarray.maxSubArray(arr5));
    }

    @Test
    void testMaxSubArrayNull() {
        int[] arr = null;
        assertEquals(0, MaximumSubarray.maxSubArray(arr));
    }

    @Test
    void testMaxSubArrayEmpty() {
        int[] arr = {};
        assertEquals(0, MaximumSubarray.maxSubArray(arr));
    }

    @Test
    void testMaxSubArrayOneElement() {
        int[] arr = {5};
        assertEquals(5, MaximumSubarray.maxSubArray(arr));

        int[] arr2 = {-5};
        assertEquals(-5, MaximumSubarray.maxSubArray(arr2));

        int[] arr3 = {0};
        assertEquals(0, MaximumSubarray.maxSubArray(arr3));
    }

    @Test
    void testMaxSubArrayPosition() {
        int[] arr = {1, 2, 3, -1, -1};
        assertEquals(6, MaximumSubarray.maxSubArray(arr));

        int[] arr2 = {-1, -1, 3, 2, 1};
        assertEquals(6, MaximumSubarray.maxSubArray(arr2));

        int[] arr3 = {-1, 4, 3, 2, 1, -1};
        assertEquals(10, MaximumSubarray.maxSubArray(arr3));

        int[] arr4 = {5, 4, 3, 2, 1, 2};
        assertEquals(17, MaximumSubarray.maxSubArray(arr4));
    }

    @Test
    void testMaxSubArrayNegative() {
        int[] arr = {-1, -2, -3, -4, -5};
        assertEquals(-1, MaximumSubarray.maxSubArray(arr));

        int[] arr2 = {-5, -4, -3, -2, -1};
        assertEquals(-1, MaximumSubarray.maxSubArray(arr2));

        int[] arr3 = {-5, -4, -3, -2, -1, 0};
        assertEquals(0, MaximumSubarray.maxSubArray(arr3));

        int[] arr4 = {-5, -4, -3, -2, -1, -2};
        System.out.println(MaximumSubarray.maxSubArray(arr4));
        assertEquals(-1, MaximumSubarray.maxSubArray(arr4));
    }

    @Test
    void testMaxSubArrayNegaiveMiddle() {
    int[] arr = {5,5,-1,-1,5,5};
    assertEquals(18, MaximumSubarray.maxSubArray(arr));

    int[] arr2 = {5,5,-1,-1,5,5,-1};
    assertEquals(18, MaximumSubarray.maxSubArray(arr2));
    }

    @Test
    void testMaxSubArrayMultipleArrays() {
        int[] arr = {10, 5, -30 ,-30, 5, 10};
        assertEquals(15, MaximumSubarray.maxSubArray(arr));
    }

    @Test
    void testMaxSubArrayZeros() {
        int[] arr = {0, 0, 0, 0, 0, 0};
        assertEquals(0, MaximumSubarray.maxSubArray(arr));

        int[] arr2 = {0, 0, 0, 0, 0, 1};
        assertEquals(1, MaximumSubarray.maxSubArray(arr2));

        int[] arr3 = {0, 0, 0, 0, 0, -1};
        assertEquals(0, MaximumSubarray.maxSubArray(arr3));

        int[] arr4 = {1, 0, 0, 0, 0};
        assertEquals(1, MaximumSubarray.maxSubArray(arr4));

        int[] arr5 = {0, 0, 1, 0};
        assertEquals(1, MaximumSubarray.maxSubArray(arr5));

        int[] arr6 = {0, -1, 0, 0};
        assertEquals(0, MaximumSubarray.maxSubArray(arr6));
    }

}
