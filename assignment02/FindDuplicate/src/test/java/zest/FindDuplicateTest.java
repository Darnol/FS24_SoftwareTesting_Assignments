package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FindDuplicateTest {
    
    @Test
    void testFindDuplicate() {
        int[] nums = {1, 3, 4, 2, 2};
        int expected = 2;
        int actual = FindDuplicate.findDuplicate(nums);
        assertEquals(expected, actual);
    }

    
    // Add more test cases as needed
    
}