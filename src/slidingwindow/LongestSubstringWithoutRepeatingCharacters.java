package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3:
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // Optimized Solution, Time O(n), space O(m), m = # of unique characters
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }

            maxLen = Math.max(maxLen, i - start + 1);
            map.put(c, i);
        }

        return maxLen;
    }

    // Naive Solution, Time O(n^2), space O(m), m = # of unique characters.
    public int lengthOfLongestSubstringII(String s) {
        if (s == null || s.length() == 0) return 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>();
            int j = i;

            for (; j < s.length(); j++) {
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (map.get(c) > 1)  break;
            }

            maxLen = Math.max(maxLen, j - i);
        }

        return maxLen;
    }
}
