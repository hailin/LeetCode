package backtrack;

import java.util.*;

/**
 * 140:
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.isEmpty()) return new ArrayList<>();

        Set<String> dict = new HashSet<>();
        Map<String, List<String>> cache = new HashMap<>();
        for (String word: wordDict) dict.add(word);

        return dfs(dict, cache, s);
    }

    private List<String> dfs(Set<String> dict, Map<String, List<String>> cache, String cur) {
        if (cache.containsKey(cur)) return cache.get(cur);

        List<String> result = new ArrayList<>();

        if (dict.contains(cur)) result.add(cur);

        for (int i = 0; i < cur.length(); i++) {
            String left = cur.substring(0, i);
            String right = cur.substring(i);

            if (!dict.contains(right)) continue;

            List<String> leftResult = dfs(dict, cache, left);

            for (String l: leftResult) {
                result.add(l + " " + right);
            }
        }

        cache.put(cur, result);
        return result;
    }
}
