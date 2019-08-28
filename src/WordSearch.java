/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * #Backtracking
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0  || word == null) return false;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (wordExist(board, word, row, col, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean wordExist(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;

        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length ||
                board[row][col] == '#' || board[row][col] != word.charAt(index))
            return false;

        char c = board[row][col];

        board[row][col] = '#';

        boolean found = wordExist(board, word, row - 1, col, index + 1) ||
                wordExist(board, word, row + 1, col, index + 1) ||
                wordExist(board, word, row, col - 1, index + 1) ||
                wordExist(board, word, row, col + 1, index + 1);

        board[row][col] = c;
        return found;
    }
}
