import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 113:
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if (root == null) return lists;

        pathSumHelper(root, sum, lists, path);
        return lists;
    }

    private void pathSumHelper(TreeNode node, int sum, List<List<Integer>> lists, List<Integer> path) {
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (sum == node.val) {
                lists.add(new ArrayList<>(path));
            }

            return;
        }

        if (node.left != null) {
            pathSumHelper(node.left, sum - node.val, lists, path);
            path.remove(path.size() - 1);
        }

        if (node.right != null) {
            pathSumHelper(node.right, sum - node.val, lists, path);
            path.remove(path.size() - 1);
        }

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
