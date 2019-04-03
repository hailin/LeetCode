import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 516:
 *
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 */
public class LongestPalindromicSubsequence {



    /**
     * Recursion with Memoization
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] mem = new int[len][len];

        for (int[] row: mem) {
            Arrays.fill(row, -1);
        }

        return longestPalindromeSubseqHelper(s, mem, 0, s.length() - 1);
    }

    public int longestPalindromeSubseqHelper(String s, int[][] mem, int start, int end) {
        if (mem[start][end] >= 0) return mem[start][end];

        if (end < start) return 0;
        if (start == end) return 1;

        if (s.charAt(start) == s.charAt(end)) {
            int max = 2 + longestPalindromeSubseqHelper(s, mem, start + 1, end - 1);
            mem[start][end] = max;
            return max;
        }


        int left = longestPalindromeSubseqHelper(s, mem, start, end - 1);
        int right = longestPalindromeSubseqHelper(s, mem, start + 1, end);

        int max = Math.max(left, right);
        mem[start][end] = max;

        return max;
    }

    /**
     * Naive solution,  TLE
     *
     * Example:
     *
     * bbbab
     * 2 + LPS(bba)
     * 2 + Max(LPS(bb), LPS(ba))
     */
    public int longestPalindromeSubseqI(String s) {
        return (longestPalindromeSubseqHelperI(s, 0, s.length() - 1));
    }


    private int longestPalindromeSubseqHelperI(String s, int start, int end) {
        if (end == start) return 1;
        if (end < start) return 0;

        if (s.charAt(start) == s.charAt(end)) {
            return 2 + longestPalindromeSubseqHelperI(s, start + 1, end - 1);
        }

        return Math.max(longestPalindromeSubseqHelperI(s, start + 1, end), longestPalindromeSubseqHelperI(s, start, end - 1));
    }
}
