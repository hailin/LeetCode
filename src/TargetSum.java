import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 494:
 *
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    // recursion with memoization
    public int findTargetSumWays(int[] nums, int S) {
        Map<String, Integer> mem = new HashMap<>();
        return findWays(nums, 0, S, mem);
    }

    private int findWays(int[] nums, int index, int sum, Map<String, Integer> mem) {
        if (mem.containsKey(index + "," + sum)) return mem.get(index + "," + sum);
        if (index == nums.length && sum == 0) return 1;
        if (index == nums.length) return 0;

        int nextIndex = index + 1;
        int num = nums[index];

        int ways = findWays(nums, nextIndex, sum - num, mem) + findWays(nums, nextIndex, sum + num, mem);
        mem.put(index + "," + sum, ways);

        return ways;
    }
}
