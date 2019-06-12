/**
 *
 * LeetCode 510:
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 * You will have direct access to the node but not to the root of the tree.
 * Each node will have a reference to its parent node.
 *
 *
 * Approach:
 *
 * The inorder successor of node X is either:
 * 1. the left most node of X's right child (inclusive)
 * 2. the smallest parent of all parents greater than X.
 *
 */
public class InorderSuccessorBSTII {
    public Node inorderSuccessor(Node x) {

        if (x == null) return null;

        Node cur = x;

        while (cur.parent != null && cur.parent.val < cur.val) {
            cur = cur.parent;
        }

        Node parent = cur.parent;

        cur = x.right;

        while (cur != null && cur.left != null) {
            cur = cur.left;
        }

        return cur != null ? cur : parent;
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        Node(int x) { val = x; }
    }
}
