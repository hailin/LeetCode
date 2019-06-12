/**
 * LeetCode 654:
 *
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 */
public class MaximumBinaryTree {

    // Time: O(n^2), Space: O(n(
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length;
        return maxBTHelper(nums, 0, len - 1);
    }

    private TreeNode maxBTHelper(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);

        int maxIndex = indexOfMax(nums, start, end);
        TreeNode node = new TreeNode(nums[maxIndex]);

        node.left = maxBTHelper(nums, start, maxIndex - 1);
        node.right = maxBTHelper(nums, maxIndex + 1, end);

        return node;
    }

    private int indexOfMax(int[] nums, int start, int end) {
        if (start > end) return -1;
        if (start == end) return start;

        int maxIndex = start;

        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
