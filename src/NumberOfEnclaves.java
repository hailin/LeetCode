/**
 * LeetCode 1020:
 *
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 *
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 *
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 * Example 2:
 *
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 *
 * Idea:
 * Turn all land's which are walk-able from boundaries into sea.
 * Then count lands left.
 *
 */
public class NumberOfEnclaves {
    private static final int[][] DIR = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] A) {
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) return 0;

        int rows = A.length;
        int cols = A[0].length;

        for (int col = 0; col < A[0].length; col++) {
            bfs(A, 0, col);
            bfs(A, rows - 1, col);
        }

        for (int row = 0; row < A.length; row++) {
            bfs(A, row, 0);
            bfs(A, row, cols - 1);
        }

        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (A[row][col] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void bfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == 0) return;

        grid[row][col] = 0;

        for (int[] dir: DIR) {
            bfs(grid, row + dir[0], col + dir[1]);
        }
    }
}
