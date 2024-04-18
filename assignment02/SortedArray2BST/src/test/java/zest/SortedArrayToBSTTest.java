package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

    @Test
    public void T4_test_pre1() {
        int[] input = new int[]{1,1};

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input);
        });
    }

    @Test
    public void T5_test_pre2() {

        List<Integer> inputIntegerList_invalid = IntStream.rangeClosed(1, 10001).boxed().toList();
        int[] input_invalid = inputIntegerList_invalid.stream().mapToInt(Integer::intValue).toArray();

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input_invalid);
        });
    }

    @Test
    public void T6_test_pre3() {

        int[] input = new int[]{2,1,3};

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input);
        });
    }

    @Test
    public void T7_test_pre4() {

        int[] input = null;

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input);
        });
    }

    @Test
    public void T8_test_pre4() {

        int[] input = null;

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input);
        });
    }

}