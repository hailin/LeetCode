package bfs;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    private static int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

         boolean[][] pacific = new boolean[rows][cols];

//         for (int col = 0; col < cols; col++) {
//             dfs(matrix, new boolean[rows][cols], pacific, 0, col);
//         }

//         System.out.println(Arrays.deepToString(pacific));

        return new ArrayList<int[]>();
    }

    public void dfs(int[][] matrix, boolean[][] visited, boolean[][] reachable, int row, int col) {
        if (visited[row][col]) return;

        for (int[] dir: DIR) {
            int x = row + dir[0];
            int y = col + dir[1];
            int h = matrix[x][y];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[x].length && !visited[x][y]) {
                visited[x][y] = true;
                if (h <= matrix[row][col]) {
                    reachable[x][y] = true;

                    dfs(matrix, visited, reachable, x, y);
                }
            }
        }
    }
}
