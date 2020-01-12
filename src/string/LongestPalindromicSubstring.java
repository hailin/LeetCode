package string;

/**
 * 5:
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    // Time O(n^2), space O(n^2), expand out the both sides.
    public String longestPalindrome(String s) {
        String max = "";

        if (s == null) return max;

        for (int i = 0; i < s.length(); i++) {
            String s1 = getPalindrome(s, i, i);
            if (s1.length() > max.length()) {
                max = s1;
            }

            String s2 = getPalindrome(s, i, i + 1);
            if (s2.length() > max.length()) {
                max = s2;
            }
        }

        return max;
    }

    private String getPalindrome(String s, int start, int end) {
        int len = s.length();

        while (start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        return s.substring(start + 1, end);
    }
}
