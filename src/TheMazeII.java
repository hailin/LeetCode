import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 505:
 *
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall. When the ball stops,
 * it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze,
 * find the shortest distance for the ball to stop at the destination.
 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded)
 * to the destination (included). If the ball cannot stop at the destination, return -1.
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
 * Output: 12
 *
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
 * Output: -1
 *
 * Explanation: There is no way for the ball to stop at the destination.
 *
 */
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
