import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 1110:
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 *
 * Idea:
 * Post Order Traversal,
 * update left & right node if node is not in delete list,
 * otherwise add left & right as roots and return null for current node.
 *
 */
public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> roots = new ArrayList<>();

        if (root == null) return roots;

        Set<Integer> toDelete = new HashSet<>();

        for (int num : to_delete) toDelete.add(num);

        if (!toDelete.contains(root.val)) roots.add(root);

        delete(toDelete, roots, root);

        return roots;
    }


    private TreeNode delete(Set<Integer> toDelete, List<TreeNode> roots, TreeNode node) {
        if (node == null) return null;

        TreeNode left = delete(toDelete, roots, node.left);
        TreeNode right = delete(toDelete, roots, node.left);

        if (!toDelete.contains(node.val)) {
            node.left = left;
            node.right = right;
            return node;
        }

        if (left != null) roots.add(left);
        if (right != null) roots.add(right);
        return null;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
