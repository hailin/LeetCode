import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {
    public static final int[][] DIR = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return true;
            }

            for (int i = 0; i < DIR.length; i++) {
                int x = cur[0];
                int y = cur[1];

                while (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] != 1) {
                    x += DIR[i][0];
                    y += DIR[i][1];
                }

                int nextX = x - DIR[i][0];
                int nextY = y - DIR[i][1];

                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return false;
    }
}
