package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the top-left corner of the map and can move one block up, down, left or right at a time. The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
 *
 * Example:
 *
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 */
public class TreasureIsland {
    public static void main(String[] args) {
        char[][] island = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}
        };

        System.out.println(treasureIsland(island));
    }

    private static final int[][] DIR = new int[][]{{0, 1,}, {0, -1}, {1, 0}, {-1, 0}};

    public static int treasureIsland(char[][] island) {
        if (island == null || island.length == 0 || island[0] == null || island[0].length == 0) return -1;

        int rows = island.length;
        int cols = island[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        visited[0][0] = true;
        queue.offer(new int[]{0, 0});

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                if (island[x][y] == 'X') return steps;

                for (int[] dir: DIR) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];

                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols &&
                            island[nextX][nextY] != 'D' && !visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
                size--;
            }

            steps++;
        }

        return -1;
    }
}
