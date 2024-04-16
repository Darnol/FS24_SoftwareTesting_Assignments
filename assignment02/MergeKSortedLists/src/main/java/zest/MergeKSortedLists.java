package zest;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {

    private int getListNodeCount(ListNode ln) {
        if (ln == null) {return 0;}
        int ct = 0;
        ListNode currentNode = ln;
        ct += 1;
        while (currentNode.next != null) {
            ct += 1;
            currentNode = currentNode.next;
        }
        return ct;
    }

    private int getMinVal(ListNode ln) {
        if (ln == null) {return 0;}
        int minVal = 0;
        ListNode currentNode = ln;
        if (currentNode.val < minVal) {minVal = currentNode.val;}
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            if (currentNode.val < minVal) {minVal = currentNode.val;}
        }
        return minVal;
    }

    private int getMaxVal(ListNode ln) {
        if (ln == null) {return 0;}
        int maxVal = 0;
        ListNode currentNode = ln;
        if (currentNode.val > maxVal) {maxVal = currentNode.val;}
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            if (currentNode.val > maxVal) {maxVal = currentNode.val;}
        }
        return maxVal;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * Merge k sorted linked lists into one sorted linked list
         *
         * @param lists the array of ListNodes (representing linked lists) to merge and sort
         *              Total size of all nodes of all lists combined must cannot exceed 10^4
         *              Each node of every linked list must have a value between -10^4 and 10^4
         *
         * @return A single ListNode linked list, merged and sorted. Empty if input is empty, null if input is null
         *
         * @throws IllegalArgumentException if the combined input size exceeds 10^4
         */

        // PRE3 : Catch null input
        if (lists == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        // Changed return value from null to empty ListNode
        if (lists.length == 0) return new ListNode();

        // PRE1 : Count all nodes combined of the input
        int MAX_INPUT_LEN = 10000;
        int ct_input = 0;
        for (ListNode node : lists) {
            if (node != null) {
                ct_input += getListNodeCount(node);
            }
        }
        if (ct_input > MAX_INPUT_LEN) {
            throw new IllegalArgumentException("Total size of combined input must be smaller or equal to 10'000");
        }

        // PRE2 : Verify all node values are between -10^4 and 10^4
        int MIN_VAL = -10000;
        int MAX_VAL = 10000;
        for (ListNode node : lists) {
            if (node != null) {
                if (getMinVal(node) < MIN_VAL) {
                    throw new IllegalArgumentException("Minimum allowed value is -10000");
                }
                if (getMaxVal(node) > MAX_VAL) {
                    throw new IllegalArgumentException("Maximum allowed value is 10000");
                }
            }
        }

        // PRE4 : Each input linked list is sorted itself.
        // TODO: Implement recursively

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null) {
                queue.add(tail.next);
            }
        }

        return dummy.next;
    }
}
