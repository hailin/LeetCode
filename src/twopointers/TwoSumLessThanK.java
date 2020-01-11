package twopointers;

import java.util.Arrays;

/**
 * 1099:
 *
 * Given an array A of integers and integer K, return the maximum S such that there exists
 * i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation:
 * We can use 34 and 24 to sum 58 which is less than 60.
 * Example 2:
 *
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation:
 * In this case it's not possible to get a pair sum less that 15.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
public class TwoSumLessThanK {
    public int twoSumLessThanK(int[] A, int K) {
        if (A == null) return -1;

        int low = 0;
        int high = A.length - 1;
        int result = Integer.MIN_VALUE;

        Arrays.sort(A);

        while (low < high) {
            int sum = A[low] + A[high];

            if (sum >= K) {
                high--;
            } else {
                result = Math.max(result, sum);
                low++;
            }
        }

        return result == Integer.MIN_VALUE ? -1 : result;
    }
}
