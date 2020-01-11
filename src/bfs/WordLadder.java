package bfs;

import java.util.*;

/**
 * 127:
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 * transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList.isEmpty()) return 0;

        Set<String> dict = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        for (String word: wordList) dict.add(word);

        if (!dict.contains(endWord)) return 0;

        queue.offer(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                String word = queue.poll();
                // if (word.equals(endWord)) return steps;

                for (int i = 0; i < word.length(); i++) {
                    char[] temp = word.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        temp[i] = c;
                        String nextWord = new String(temp);

                        if (dict.contains(nextWord)) {
                            if (nextWord.equals(endWord)) return steps + 1;

                            queue.offer(nextWord);
                            dict.remove(nextWord);
                        }
                    }
                }

                size--;
            }

            steps++;
        }

        return 0;
    }
}
