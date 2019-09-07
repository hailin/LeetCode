/**
 *
 * LeetCode 211:
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class AddAndSearchWordDataStructureDesign {
    TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWordDataStructureDesign() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode cur = root;

        for (char c: chars) {
            if (cur.nodes[c - 'a'] == null) {
                cur.nodes[c - 'a'] = new TrieNode();
            }

            cur = cur.nodes[c - 'a'];
        }

        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        return searchHelper(root, chars, 0);
    }

    private boolean searchHelper(TrieNode cur, char[] chars, int index) {
        if (index == chars.length) return cur.isWord;

        char c = chars[index];

        if (c != '.') {
            return cur.nodes[c - 'a'] != null && searchHelper(cur.nodes[c - 'a'], chars, index + 1);
        }

        for (TrieNode node: cur.nodes) {
            if(node != null && searchHelper(node, chars, index + 1)) {
                return true;
            }
        }

        return false;
    }

    private class TrieNode {
        TrieNode[] nodes;
        boolean isWord;

        TrieNode() {
            nodes = new TrieNode[26];
            isWord = false;
        }
    }
}
