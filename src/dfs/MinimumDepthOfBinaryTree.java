package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111:
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 * return its minimum depth = 2.
 */
public class MinimumDepthOfBinaryTree {
    // DFS
    public int minDepthI(TreeNode root) {
        if (root == null) return 0;

        int minLeft = minDepthI(root.right);
        int minRight = minDepthI(root.left);

        if (root.left == null) return 1 + minLeft;

        if (root.right == null) return 1 + minRight;

        return 1 + Math.min(minLeft, minRight);
    }

    // BFS
    public int minDepthII(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode node = queue.poll();

                if (node.left == null && node.right == null) return depth;

                if (node.left != null) queue.offer(node.left);

                if (node.right != null) queue.offer(node.right);

                size--;
            }

            depth++;
        }

        return depth;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
