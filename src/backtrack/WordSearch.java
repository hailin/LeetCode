package backtrack;

/**
 * 79:
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
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
 */
public class WordSearch {
    private static final int[][] DIR = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null) {
            return false;
        }

        int rows = board.length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (existsWord(board, word, row, col, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existsWord(char[][] board, String word, int row, int col, int i) {
        if (i >= word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length || board[row][col] == '#' || board[row][col] != word.charAt(i)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (existsWord(board, word, nextRow, nextCol, i + 1)) {
                return true;
            }
        }

        board[row][col] = temp;
        return false;
    }
}
