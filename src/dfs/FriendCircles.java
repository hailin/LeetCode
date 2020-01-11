package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 547:
 *
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B,
 * and B is a direct friend of C, then A is an indirect friend of C. And we defined a
 * friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles {
    // Time: O(n^2), Space: O(n)
    public int findCircleNumII(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null) return -1;

        int n = M.length;
        Map<Integer, Set<Integer>> graph = buildGraph(M);
        boolean[][] visited = new boolean[n][n];
        int count = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < M[row].length; col++) {
                if (!visited[row][col] && M[row][col] == 1) {
                    dfs(graph, visited, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, boolean[][] visited, int row, int col) {
        visited[row][col] = true;

        for (int c: graph.get(row)) {
            if (!visited[row][c]) {
                dfs(graph, visited, row, c);
            }
        }

        for (int r: graph.get(col)) {
            if (!visited[r][col]) {
                dfs(graph, visited, r, col);
            }
        }
    }

    /**
     *   [[1,1,0],
     *    [1,1,1],
     *    [0,1,1]]
     */

    private Map<Integer, Set<Integer>> buildGraph(int[][] M) {
        int n = M.length;
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (M[row][col] == 1) {
                    graph.get(row).add(col);
                    graph.get(col).add(row);
                }
            }
        }

        return graph;
    }

    // Time O(n^3), Space O(n^2)
    public int findCircleNumIII(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null) return -1;

        int rows = M.length;
        int cols = M[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < M[row].length; col++) {
                if (!visited[row][col] && M[row][col] == 1) {
                    dfs(M, visited, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] M, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        int rows = M.length;
        int cols = M[row].length;


        for (int i = 0; i < rows; i++) {
            if (!visited[i][col] && M[i][col] == 1) {
                dfs(M, visited, i, col);
            }
        }

        for (int i = 0; i < cols; i++) {
            if (!visited[row][i] && M[row][i] == 1) {
                dfs(M, visited, row, i);
            }
        }
    }
}
