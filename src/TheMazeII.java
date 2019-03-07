import java.util.Arrays;
import java.util.PriorityQueue;

public class TheMazeII {
    public static final int[][] DIR = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] costs = new int[maze.length][maze[0].length];

        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        costs[start[0]][start[1]] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0], start[1], 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : DIR) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                int weight = 0;

                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    weight++;
                }

                int nextX = x - dir[0];
                int nextY = y - dir[1];

                if (costs[cur[0]][cur[1]] + weight < costs[nextX][nextY]) {
                    costs[nextX][nextY] = costs[cur[0]][cur[1]] + weight;
                    queue.offer(new int[]{nextX, nextY, costs[nextX][nextY]});
                }
            }
        }

        return costs[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : costs[destination[0]][destination[1]];
    }
}
