package zest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.jqwik.api.constraints.*;
import net.jqwik.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class MergeKSortedListsTest {

    MergeKSortedLists merger = new MergeKSortedLists();

    public static ListNode createListNode(List<Integer> input) {

        // Reverse the input
        List<Integer> reversed_input = new ArrayList<>(input);
        Collections.reverse(reversed_input);

        // Create ListNode to capture the current next and the final output ListNode
        ListNode current_next = new ListNode();
        ListNode output_listNode = new ListNode();

        // Loop through input in reverse order
        for (int i = 0; i < reversed_input.size(); i++) {

            // Get value for new node
            int i_val;
            i_val = reversed_input.get(i);

            ListNode i_listNode = new ListNode(i_val);

            if (i > 0) {
                i_listNode.next = current_next;
            }

            current_next = i_listNode;
            output_listNode = i_listNode;
        }

        return output_listNode;
    }

    public static List<Integer> getListNodeElements(ListNode listNode_input) {

        // if the input is null return an empty list
        if (listNode_input == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> result = new ArrayList<>();
        ListNode currentNode;
        ListNode nextNode;
        currentNode = listNode_input;
        nextNode = currentNode.next;
        if (currentNode == null) {
            return result;
        } else {
            result.add(currentNode.val);
        }
        while (nextNode != null) {
            currentNode = nextNode;
            result.add(currentNode.val);
            nextNode = currentNode.next;
        }
        return result;
    }

    @Test
    public void test_getListNodeElements_empty() {
        ListNode emptyListNode = new ListNode();
        List<Integer> result = getListNodeElements(emptyListNode);
        List<Integer> expected = List.of(0);
        assertEquals(expected, result);
    }

    @Test
    public void test_helpers() {
        List<Integer> exp = List.of(0,1,1,2,3,4,4,5,6);
        ListNode expected = createListNode(exp);

        List<Integer> i0 = List.of();
        List<Integer> i1 = List.of(1,4,5);
        List<Integer> i2 = List.of(1,3,4);
        List<Integer> i3 = List.of(2,6);

        List<List<Integer>> inputs = List.of(i0,i1,i2,i3);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);

        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T1_1_test_null() {
        ListNode[] null_input = null;

        // Task1 Test implementation
        // assertNull(merger.mergeKLists(null_input));

        // Task2/3 Test implementation to assert exception
        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(null_input);
        });
    }

    @Test
    public void T1_2_test_empty() {
        ListNode expected = new ListNode();
        ListNode[] empty_input = new ListNode[0];
        ListNode result = merger.mergeKLists(empty_input);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T2_test_valid_singleInput_singleLink() {
        /*
        Test input where it is a single linked list with no link
         */
        List<Integer> exp = List.of(1);
        ListNode expected = createListNode(exp);

        List<Integer> i1 = List.of(1);

        List<List<Integer>> inputs = List.of(i1);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T3_test_valid_singleInput_multiLink() {
        /*
        Test input where it is a single linked list with links
         */
        List<Integer> i1 = List.of(3,1,2);

        List<List<Integer>> inputs = List.of(i1);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });

    }

    @Test
    public void T4_test_valid_multiInput_singleLink() {
        /*
        Test input where it is multiple linked lists, each with no links
         */
        List<Integer> exp = List.of(10,20,30);
        ListNode expected = createListNode(exp);

        List<Integer> i1 = List.of(10);
        List<Integer> i2 = List.of(30);
        List<Integer> i3 = List.of(20);

        List<List<Integer>> inputs = List.of(i1,i2,i3);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T5_test_valid_multiInput_mixLink() {
        /*
        Test input where it is multiple linked lists, mixing no links and links
         */
        List<Integer> exp = List.of(10,20,30);
        ListNode expected = createListNode(exp);

        List<Integer> i1 = List.of(20);
        List<Integer> i2 = List.of(10, 30);

        List<List<Integer>> inputs = List.of(i1,i2);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T6_test_valid_multiInput_multiLink() {
        /*
        Test input where it is multiple linked lists, all containing links
         */
        List<Integer> exp = List.of(10,20,30,40);
        ListNode expected = createListNode(exp);

        List<Integer> i1 = List.of(20,40);
        List<Integer> i2 = List.of(10, 30);

        List<List<Integer>> inputs = List.of(i1,i2);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T7_test_invalid_notSortedInput() {
        /*
        Test input that is a linked list, but not sorted
         */
        List<Integer> i1 = List.of(10);
        List<Integer> i2 = List.of(30,20);

        List<List<Integer>> inputs = List.of(i1,i2);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });
    }

    @Test
    public void T8_test_invalid_notSortedInput_orderReversed() {
        /*
        Test input that is a linked list, but not sorted
         */
        List<Integer> i1 = List.of(30,20);
        List<Integer> i2 = List.of(10);

        List<List<Integer>> inputs = List.of(i1,i2);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];

        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });
    }

    @Test
    public void T9_test_pre1_inputSize() {

        List<Integer> i_invalid = Collections.nCopies(10001, 1);
        List<List<Integer>> inputs = List.of(i_invalid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });
    }

    @Test
    public void T10_test_pre1_inputSize_valid() {

        List<Integer> i_valid = Collections.nCopies(10000, 1);

        List<Integer> exp = new ArrayList<>(i_valid);
        ListNode expected = createListNode(exp);

        List<List<Integer>> inputs = List.of(i_valid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T11_test_pre2_minVal() {

        List<Integer> i_invalid = List.of(-10001,1,0,-1);
        List<List<Integer>> inputs = List.of(i_invalid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });
    }

    @Test
    public void T12_test_pre2_minVal_valid() {

        List<Integer> exp = List.of(-10000,-1,0,1);
        ListNode expected = createListNode(exp);

        List<Integer> i_invalid = List.of(-10000,-1,0,1);
        List<List<Integer>> inputs = List.of(i_invalid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }

    @Test
    public void T13_test_pre2_maxVal() {

        List<Integer> i_invalid = List.of(10001,1,0,-1);
        List<List<Integer>> inputs = List.of(i_invalid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            merger.mergeKLists(inputs_listNode);
        });
    }

    @Test
    public void T14_test_pre2_maxVal_valid() {

        List<Integer> exp = List.of(-1,0,1,10000);
        ListNode expected = createListNode(exp);

        List<Integer> i_invalid = List.of(-1,0,1,10000);
        List<List<Integer>> inputs = List.of(i_invalid);
        ListNode[] inputs_listNode = new ListNode[inputs.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = inputs.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(expected), getListNodeElements(result));
    }




    // PROPERTY BASED TESTS
    @Property(tries = 1000)
    void sameLengthAndSorted(
            @ForAll
            @Size(max = 3) List<@Size(min = 0, max = 10) List<@IntRange(min=-10000, max=10000) Integer>> listOfIntegerLists
    ) {

        // This list will hold the expected result, which is one big list, sorted
        List<Integer> listExpected = new ArrayList<>();

        // make sure the input is sorted, each of the generated lists must be sorted
        for (List<Integer> integerList : listOfIntegerLists) {
            // if the integerList is empty, do add a zero, because that is what
            if (!integerList.isEmpty()) {
                Collections.sort(integerList); // first sort
                listExpected.addAll(integerList); // after sort add to expected result
            } else {
                listExpected.add(0); // if the interList is empty, add a dummy 0
            }
        }

        // Create the expected listNode from the expected list of integers, first sort
        Collections.sort(listExpected);
        ListNode listNodeExpected = createListNode(listExpected);

        // create linked list from the set of sorted integer lists
        ListNode[] inputs_listNode = new ListNode[listOfIntegerLists.size()];
        for (int i = 0; i < inputs_listNode.length; i++) {
            List<Integer> l = listOfIntegerLists.get(i);
            inputs_listNode[i] = createListNode(l);
        }

        // test. also account for empty input
        ListNode result = merger.mergeKLists(inputs_listNode);
        assertEquals(getListNodeElements(listNodeExpected), getListNodeElements(result));

    }

}