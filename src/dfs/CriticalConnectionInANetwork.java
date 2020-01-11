package dfs;

import java.util.*;

public class CriticalConnectionInANetwork {
    int nodeId = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> bridges = new ArrayList<>();
        if (connections == null || connections.isEmpty()) return bridges;

        Map<Integer, Set<Integer>> graph = buildGraph(n, connections);
        boolean[] visited = new boolean[n];
        int[] ids = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;

        dfs(graph, bridges, visited, ids, parent, low, 0);

        return bridges;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, List<List<Integer>> bridges,
                     boolean[] visited, int[] ids, int[] parent, int[] low, int u) {
        visited[u] = true;
        ids[u] = low[u] = nodeId++;

        for (int v: graph.get(u)) {
            if (v == parent[u]) continue;

            if (!visited[v]) {
                parent[v] = u;

                dfs(graph, bridges, visited, ids, parent, low, v);
                low[u] = Math.min(low[u], low[v]);

                if (ids[u] < low[v]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else {
                low[u] = Math.min(low[u], low[v]);
            }
        }
    }

    private Map<Integer, Set<Integer>> buildGraph(int n, List<List<Integer>> edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (List<Integer> edge: edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
