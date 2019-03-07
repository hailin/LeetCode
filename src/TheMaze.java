import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 490:
 *
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall. When the ball stops,
 * it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze,
 * determine whether the ball could stop at the destination.
 *
 * The maze is represented by a binary 2D array.
 * 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: true
 *
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 *
 * Output: false
 *
 * Explanation: There is no way for the ball to stop at the destination.
 *
 */

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
