package priorityqueue;

import java.util.*;

/**
 * 763:
 *
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() == 0) return result;

        int[] lastPos = new int[26];

        for (int i = 0; i < S.length(); i++) {
            lastPos[S.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0;

        for (int i = 0; i < S.length(); i++) {
            end = Math.max(lastPos[S.charAt(i) - 'a'], end);

            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }

        return result;
    }

    public List<Integer> partitionLabelsII(String S) {
        List<Integer> result = new ArrayList<>();

        if (S == null || S.length() == 0) return result;

        PriorityQueue<int[]> queue = buildQueue(S);
        List<int[]> intervals = new ArrayList<>();
        intervals.add(queue.poll());

        while (!queue.isEmpty()) {
            int[] newInterval = queue.poll();
            int[] lastInterval = intervals.get(intervals.size() - 1);

            if (newInterval[0] <= lastInterval[1]) {
                lastInterval[0] = Math.min(lastInterval[0], newInterval[0]);
                lastInterval[1] = Math.max(lastInterval[1], newInterval[1]);
            } else {
                intervals.add(newInterval);
            }
        }

        for (int[] interval: intervals) {
            result.add(interval[1] - interval[0] + 1);
        }

        return result;
    }

    private PriorityQueue<int[]> buildQueue(String S) {
        Map<Character, Integer> start = new HashMap<>();
        Map<Character, Integer> end = new HashMap<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (!start.containsKey(c)) {
                start.put(c, i);
            }

            end.put(c, i);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(interval -> interval[0]));

        for (Map.Entry<Character, Integer> entry: start.entrySet()) {
            int[] interval = new int[]{start.get(entry.getKey()), end.get(entry.getKey())};
            queue.offer(interval);
        }

        return queue;
    }
}
