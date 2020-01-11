package dp;

/**
 * 562:
 *
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOneInMatrix {
    // Time O(rows*cols), Space O(cols*4)
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
        int rows = M.length;
        int cols = M[0].length;

        int[][] dpPrev = new int[cols][4];
        int max = 0;

        for (int row = 0; row < rows; row++) {
            int[][] dpCur = new int[cols][4];

            for (int col = 0; col < cols; col++) {
                if (M[row][col] == 0) continue;

                // vertical
                dpCur[col][0] = dpPrev[col][0] + 1;

                // horizontal, boundary check
                dpCur[col][1] = col - 1 < 0 ? 1 : dpCur[col - 1][1] + 1;

                // diagonal, boundary check
                dpCur[col][2] = col - 1 < 0 ? 1 : dpPrev[col - 1][2] + 1;

                // reverse-diagonal, boundary check
                dpCur[col][3] = col + 1 >= cols ? 1 : dpPrev[col + 1][3] + 1;

                for (int i = 0;  i < 4; i++) {
                    max = Math.max(max, dpCur[col][i]);
                }
            }

            dpPrev = dpCur;
        }

        return max;
    }

    private static final int[][] DIR = {
            {-1, 0},  // vertical top
            {0, -1},  // horizontal left
            {-1, -1}, // diagonal top left
            {-1, 1}, // diagonal top right
    };

    // Time O(rows*cols), space O(rows*cols)
    public int longestLineII(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;

        int rows = M.length;
        int cols = M[0].length;

        int[][][] dp = new int[rows][cols][4];
        int max = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (M[row][col] == 0) continue;
                for (int i = 0; i < 4; i++) {
                    int[] dir = DIR[i];
                    int prevX = row + dir[0];
                    int prevY = col + dir[1];

                    if (prevX >= 0 && prevX < rows && prevY >= 0 && prevY < cols) {
                        dp[row][col][i] = dp[prevX][prevY][i] + 1;
                    } else {
                        dp[row][col][i] = 1;
                    }

                    max = Math.max(dp[row][col][i], max);
                }
            }
        }

        return max;
    }
}
