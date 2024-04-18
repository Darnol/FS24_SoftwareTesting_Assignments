package zest;
import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
    int val;
    zest.TreeNode left;
    zest.TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {

    public zest.TreeNode sortedArrayToBST(int[] nums) {

        // PRE4 : The input must not be null
        if (nums == null) {
            throw new IllegalArgumentException("The input must not be null");
        }

        // PRE2 : The length of the input must be between 0 and 10'000
        if (nums.length > 10000) {
            throw new IllegalArgumentException("The length of the input must be between 0 and 10'000");
        }

        // PRE1 : The input must contain unique integers
        if (Arrays.stream(nums).distinct().count() != nums.length) {
            throw new IllegalArgumentException("The input must contain unique integers");
        }

        // PRE3 : The input must be sorted ascending
        int[] nums_sorted = nums.clone();
        Arrays.sort(nums_sorted);
        if (!Arrays.equals(nums_sorted, nums)) {
            throw new IllegalArgumentException("The input must be sorted ascending");
        }

        // Construct the BST
        TreeNode result = constructBSTRecursive(nums, 0, nums.length - 1);
        List<Integer> resultIntegerList = levelOrderTraversal(result);

        // POST1 : The output has the same length as the input
        if (!(resultIntegerList.size() == nums.length)) {
            throw new IllegalStateException("The output must have the same length as the input");
        }

        // POST2 : The unique set of the output is equal to the unique set of the input
        List<Integer> numsIntegerList = Arrays.stream(nums).boxed().toList();
        Set<Integer> resultSet = new HashSet<>(resultIntegerList);
        Set<Integer> numsSet = new HashSet<>(numsIntegerList);
        if (!(resultSet.equals(numsSet))) {
            throw new IllegalStateException("The output must have the same unique values as the input");
        }

        return result;
    }

    private void checkInvariant_1_2(TreeNode node) {
        if (!(node.left == null)) {
            if (node.left.val >= node.val) {
                throw new IllegalStateException("left value of node is bigger than value");
            }
        }
        if (!(node.right == null)) {
            if (node.right.val <= node.val) {
                throw new IllegalStateException("right value of node is bigger than value");
            }
        }
    }

    private zest.TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        zest.TreeNode node = new zest.TreeNode(nums[mid]);

        // INV1 : left must be smaller than val
        // INV2 : right must be bigger than val
        checkInvariant_1_2(node);

        node.left = constructBSTRecursive(nums, left, mid - 1);
        node.right = constructBSTRecursive(nums, mid + 1, right);

        // INV1 : left must be smaller than val
        // INV2 : right must be bigger than val
        checkInvariant_1_2(node);

        return node;
    }

    public List<Integer> levelOrderTraversal(zest.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<zest.TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            zest.TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }
}
