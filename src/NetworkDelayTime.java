import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge: times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        costs[K] = 0;
        queue.offer(new int[]{K, 0});


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (!graph.containsKey(cur[0])) continue;

            for (int[] next: graph.get(cur[0])) {
                if (cur[1] + next[1] < costs[next[0]]) {
                    costs[next[0]] = cur[1] + next[1];
                    queue.offer(new int[]{next[0], costs[next[0]]});
                }
            }
        }

        int max = 0;

        for (int i = 1; i <= N; i++) {
            if (costs[i] == Integer.MAX_VALUE) {
                return -1;
            }

            max = Math.max(max, costs[i]);
        }

        return max;
    }
}
