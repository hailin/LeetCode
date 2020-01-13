package dp;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String max = "";

        if (s == null || s.length() == 0) return "";

        int len = s.length();

        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j == 0) {
                    dp[i][j] = true;
                } else if (i - j < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i - 1][j + 1];
                }

                if (dp[i][j] && (i - j + 1) > max.length()) {
                    max = s.substring(j, i + 1);
                }
            }
        }

        return max;
    }
}
