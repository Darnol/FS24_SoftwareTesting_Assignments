package zest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
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
    public void T3_1_test_len_multi() {

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
    public void T8_test_post1_post2() {

        int[] input = new int[]{-10, -3, 0, 5, 9};

        // expected
        int expectedLength = input.length;
        Set<Integer> expectedIntegerSet = new HashSet<>(Arrays.stream(input).boxed().toList());

        TreeNode root = converter.sortedArrayToBST(input);
        List<Integer> result = converter.levelOrderTraversal(root);

        Set<Integer> resultIntegerSet = new HashSet<>(result);

        assertEquals(expectedLength, input.length);
        assertEquals(expectedIntegerSet, resultIntegerSet);
    }


    // PROPERTY-BASED TESTS
    @Property(tries = 1000)
    @Report(Reporting.GENERATED)
    void test_validInputs(
        @ForAll
        @Size(max = 5)
        @UniqueElements
        List<@IntRange(min=0, max=10000) Integer> inputIntegerList
    ) {

        // Sort the input
        Collections.sort(inputIntegerList);

        // input to int array
        int[] input = inputIntegerList.stream().mapToInt(Integer::intValue).toArray();

        // expected
        int expectedLength = input.length;
        Set<Integer> expectedIntegerSet = new HashSet<>(Arrays.stream(input).boxed().toList());

        TreeNode root = converter.sortedArrayToBST(input);
        List<Integer> result = converter.levelOrderTraversal(root);

        Set<Integer> resultIntegerSet = new HashSet<>(result);

        assertEquals(expectedLength, input.length);
        assertEquals(expectedIntegerSet, resultIntegerSet);
    }

    @Property(tries = 1000)
    @Report(Reporting.GENERATED)
    void test_invalidInputs_notUniqueNorSorted(
            @ForAll
            @Size(min=1, max = 5) // do not consider empty list as input, min > 0
            List<@IntRange(min=0, max=100) Integer> inputIntegerList
            // Remove @UniqueElements annotation
    ) {

        // Sort the input in descending order, first sort then reverse
        Collections.sort(inputIntegerList);
        Collections.reverse(inputIntegerList);

        // Add at least one duplicated element to list
        inputIntegerList.add(inputIntegerList.get(0));

        // input to int array
        int[] input = inputIntegerList.stream().mapToInt(Integer::intValue).toArray();

        assertThrows(IllegalArgumentException.class, () -> {
            converter.sortedArrayToBST(input);
        });

    }





}