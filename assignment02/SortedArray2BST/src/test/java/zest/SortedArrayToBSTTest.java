package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class SortedArrayToBSTTest {

    SortedArrayToBST converter = new SortedArrayToBST();

    // TASK 1 : Reach 100% code coverage
    @Test
    public void T1_test_empty() {

        int[] input = new int[]{};

        // expected
        List<Integer> expected = new ArrayList<>();

        TreeNode root = converter.sortedArrayToBST(input);
        List<Integer> result = converter.levelOrderTraversal(root);

        assertEquals(expected,result);

    }

    @Test
    public void T2_test_len_one() {

        int[] input = new int[]{1};

        // expected
        List<Integer> expected = new ArrayList<>(List.of(1));

        TreeNode root = converter.sortedArrayToBST(input);
        List<Integer> result = converter.levelOrderTraversal(root);

        assertEquals(expected,result);

    }

    @Test
    public void T3_test_len_multi() {

        int[] input = new int[]{-10, -3, 0, 5, 9};

        // expected
        List<Integer> expected = new ArrayList<>(List.of(0, -10, 5, -3, 9));

        TreeNode root = converter.sortedArrayToBST(input);
        List<Integer> result = converter.levelOrderTraversal(root);

        assertEquals(expected,result);

    }
 
}