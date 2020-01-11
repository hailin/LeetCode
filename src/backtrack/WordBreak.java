package backtrack;

import java.util.*;

/**
 * 139:
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.isEmpty()) return false;

        Set<String> dict = new HashSet<>();
        Map<String, Boolean> cache = new HashMap<>();

        for (String word: wordDict) {
            dict.add(word);
        }

        return canBreak(dict, cache, s);
    }

    private boolean canBreak(Set<String> dict, Map<String, Boolean> cache, String cur) {
        if (cache.containsKey(cur)) return cache.get(cur);

        if (dict.contains(cur)) return true;

        for (int i = 1; i < cur.length(); i++) {
            String left = cur.substring(0, i);
            String right = cur.substring(i);

            if (dict.contains(left) && canBreak(dict, cache, right)) return true;
        }

        cache.put(cur, false);
        return false;
    }
}
