package prefixtrie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearchII {
    private static final int[][] DIR = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        Trie prefixTrie = new Trie();

        for (String word: words) prefixTrie.add(word);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                dfs(board, result, prefixTrie.root, row, col);
            }
        }

        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, Set<String> result, TrieNode cur, int row, int col) {
        if (cur.isWord) {
            result.add(cur.word);
        }

        if (row < 0 || row >= board.length ||
                col < 0 || col >= board[row].length ||
                board[row][col] == '#' ||
                cur.children[board[row][col] - 'a'] == null
        ) return;

        char temp = board[row][col];
        cur = cur.children[temp - 'a'];
        board[row][col] = '#';

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            dfs(board, result, cur, nextRow, nextCol);
        }

        board[row][col] = temp;
    }

    private class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void add(String word) {
            TrieNode cur = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }

                cur = cur.children[c - 'a'];
            }

            cur.isWord = true;
            cur.word = word;
        }
    }

    private class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
            word = null;
        }
    }
}
