import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Leetcode 212:
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
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
public class WorkSearchII {
    private static final int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Optimized solution. This significantly reduced runtime when pool of words gets larger & when there's common prefix
     * between these words. Only DFS(Search) once for common prefix words.
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        TrieNode root = buildPrefixTrie(words);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                find(board, result, root, "", row, col);
            }
        }

        return new ArrayList<>(result);
    }

    public void find(char[][] board, Set<String> result, TrieNode cur, String curStr, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 ||
            col >= board[row].length || board[row][col] == '#' ||
            cur.children[board[row][col] - 'a'] == null)
            return;

        int index = board[row][col] - 'a';
        curStr += board[row][col];

        if (cur.children[index].isWord) {
            result.add(curStr);
        }

        char value = board[row][col];
        board[row][col] = '#';

        for (int[] dir: DIR) {
            find(board, result, cur.children[index], curStr, row + dir[0], col + dir[1]);
        }

        board[row][col] = value;
    }

    private TrieNode buildPrefixTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word: words) {
            addWord(root, word);
        }

        return root;
    }

    private void addWord(TrieNode root, String word) {
        TrieNode cur = root;
        char[] chars = word.toCharArray();

        for (char c: chars) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }

            cur = cur.children[index];
        }

        cur.isWord = true;
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    /**
     * Naive solution, brute force
     */
    public List<String> findWordsII(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        for (String word: words) {
            if (findWord(board, word)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean findWord(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (findWordHelper(board, word, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWordHelper(char[][] board, String word, int row, int col, int index) {
        if (index >= word.length()) return true;

        if (row < 0 || row >= board.length ||
                col < 0 || col >= board[row].length ||
                board[row][col] != word.charAt(index))
            return false;

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = findWordHelper(board, word, row - 1, col, index + 1) ||
                findWordHelper(board, word, row + 1, col, index + 1) ||
                findWordHelper(board, word, row, col - 1, index + 1) ||
                findWordHelper(board, word, row, col + 1, index + 1);

        board[row][col] = temp;
        return found;
    }
}
