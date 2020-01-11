package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!canFinish(graph, visited, visiting, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean canFinish(Map<Integer, Set<Integer>> graph, boolean[] visited, boolean[] visiting, int u) {
        if (visited[u]) return true;
        if (visiting[u]) return false;

        visiting[u] = true;

        for (int v: graph.get(u)) {
            if (!canFinish(graph, visited, visiting, v)) {
                return false;
            }
        }

        visiting[u] = false;
        visited[u] = true;
        return true;
    }

    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());

        for (int[] prereq: prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }

        return graph;
    }
}
