package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

        int[][] DIR = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new int[] {row, col});
                }

                if (grid[row][col] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0;

        int mins = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                int[] cur = queue.poll();

                for (int[] dir: DIR) {
                    int row = cur[0] + dir[0];
                    int col = cur[1] + dir[1];

                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] == 1) {
                        queue.offer(new int[] {row, col});
                        grid[row][col] = 2;
                        freshCount--;
                    }
                }

                size--;
            }
            mins++;
        }

        return freshCount == 0 ? mins : -1;
    }
}
