import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 541:
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input: [[0,0,0,0,1],[0,1,0,1,0],[0,1,1,1,1]]
 * Ouput: [[0,0,0,0,1],[0,1,0,1,0],[0,1,1,2,1]]
 *
 *
 * Reverse thinking,
 * Put smallest distance cell to queue,
 * and check if neighbor distance is more than 1 plus current cell's distance.
 * (neighbor could get smaller distance coming from cur cell)
 */
public class ZeroOneMatrix {
    private static final int[][] DIR = new int[][] {{-1, 0},{1, 0}, {0, 1}, {0, -1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return matrix;

        int rows = matrix.length;
        int cols = matrix[0].length;

        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    queue.offer(row * cols + col);
                } else {
                    matrix[row][col] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / cols;
            int y = cur % cols;

            for (int[] dir: DIR) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && matrix[nx][ny] > 0 && matrix[nx][ny] > matrix[x][y] + 1) {
                    matrix[nx][ny] = matrix[x][y] + 1;
                    queue.offer(nx * cols + ny);
                }
            }
        }

        return matrix;
    }
}
