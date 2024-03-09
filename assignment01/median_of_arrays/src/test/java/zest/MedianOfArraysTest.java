package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/*Categories

Special cases
array contains null
1 or 2 null,
1 or 2 not sorted
Both empty

all same number

Even/uneven

array lenght: empty, one, >one

Negative

all numbers in one array bigger than the other */
class MedianOfArraysTest {
    @Test
    void testFindMedianSortedArraysNull() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = null;
        int[] nums2 = {1};
        assertEquals(0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1};
        nums2 = null;
        assertEquals(0, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void testFindMedianSortedArraysNotSorted() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {4, 2};
        assertEquals(0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{3, 1};
        nums2 = new int[]{2, 4};
        assertEquals(0, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void testFindMedianSortedBothArraysEmpty() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {};
        int[] nums2 = {};
        assertEquals(0, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void testFindMedianSortedArraysOneEmpty() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {1};
        int[] nums2 = {};
        assertEquals(1.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{};
        nums2 = new int[]{1};
        assertEquals(1.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{};
        nums2 = new int[]{1, 2};
        assertEquals(1.5, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }


    @Test
    void testFindMedianSortedArraysOneArrayBigger
    () {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4, 5};
        assertEquals(3.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{3, 4, 5};
        nums2 = new int[]{1, 2};
        assertEquals(3, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{4, 5, 6};
        nums2 = new int[]{1, 2, 3};
        assertEquals(3.5, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }
    @Test
    void testFindMedianSortedArraysAllSameNumber() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {1, 1, 1};
        int[] nums2 = {1, 1 ,1};
        assertEquals(1.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{2, 2};
        nums2 = new int[]{2, 2 ,2};
        assertEquals(2.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void testFindMedianSortedArrays() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        assertEquals(2.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));
    }

   @Test
    void testFindMedianSortedArraysNegative() {
        MedianOfArrays medianOfArrays = new MedianOfArrays();
        int[] nums1 = {-3, -1};
        int[] nums2 = {-2};
        assertEquals(-2.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{-3, -1};
        nums2 = new int[]{-4, -2};
        assertEquals(-2.5, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{-3, -1};
        nums2 = new int[]{1, 3};
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1, 3};
        nums2 = new int[]{-3, -1, 0};
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(nums1, nums2));

    }


}