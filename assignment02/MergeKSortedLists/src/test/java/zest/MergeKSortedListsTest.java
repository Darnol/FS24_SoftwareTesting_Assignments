package zest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MergeKSortedListsTest {

    @Test
    public void test_dummy() {
        assertTrue(true);
    }

    @Test
    public void test_success() {

        ListNode[] input = new ListNode[3];

        List<int[]> input_arrays = new ArrayList<int[]>();

        int[] i1 = {1,4,5};
        int[] i2 = {1,3,4};
        int[] i3 = {2,6};

        input_arrays.add(i1);
        input_arrays.add(i2);
        input_arrays.add(i3);

        for (int[] input_array : input_arrays) {
            ListNode[] tmp_input = new ListNode[input_array.length];
            // Reverse the input_array and create LinkedList
            Collections.reverse(Arrays.asList(input_arrays));
            ListNode newList = new ListNode();
            for (int i = 0; i < input_array.length; i++) {
                int input_val = input_array[i];
                if (i == 0) {
                    newList = new ListNode(input_val);
                } else {
                    newList = new ListNode(input_val, newList);
                }
                tmp_input[i] = newList;
            }
        }





//        MergeKSortedLists mergerInstance = new MergeKSortedLists();

    }
}