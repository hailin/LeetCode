package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rock
 * s and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find
 * the treasure. So you must figure out a shortest route to one of the treasure islands.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters.
 * You must start from one of the starting point (marked as S) of the map and can move one block up, down,
 * left or right at a time. The treasure island is marked as X. Any block with dangerous rocks or reefs
 * will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are
 * safe to sail in. Output the minimum number of steps to get to any of the treasure islands.
 *
 * Example:
 *
 * Input:
 * [['S', 'O', 'O', 'S', 'S'],
 *  ['D', 'O', 'D', 'O', 'D'],
 *  ['O', 'O', 'O', 'O', 'X'],
 *  ['X', 'D', 'D', 'O', 'O'],
 *  ['X', 'D', 'D', 'D', 'O']]
 *
 * Output: 3
 * Explanation:
 * You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0).
 * Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
 */
public class TreasureIslandII {
    private static final int[][] DIR = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        char[][] island = new char[][]{
                {'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'D'},
                {'O', 'O', 'O', 'X', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}
        };

        System.out.println(treasureIsland(island));
    }

    public static int treasureIsland(char[][] island) {
        if (island == null || island.length == 0 || island[0] == null || island[1].length == 0) return -1;

        int rows = island.length;
        int cols = island[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < island[row].length; col++) {
                if (island[row][col] == 'S') {
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

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

                    if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols ||
                            visited[nextX][nextY] || island[nextX][nextY] == 'D')
                        continue;

                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
                size--;
            }

            steps++;
        }

        return -1;
    }
}
