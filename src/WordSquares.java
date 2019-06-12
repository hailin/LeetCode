import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * LeetCode 425:
 *
 * Given a set of words (without duplicates),
 * find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads
 * the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares.
 * The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 *
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares.
 * The order of output does not matter (just the order of words in each word square matters).
 *
 *
 * Thoughts:
 *
 * b a l l
 * the second word must start with a, which must be area,
 * b *a* l l
 * a r e a
 * the third word must start with le,  which must be lead
 * b a *l* l
 * a r *e* a
 * l e a d
 * the fourth word must start with lad, which must be lady
 * b a l *l*
 * a r e *a*
 * l e a *d*
 * l a d y
 *
 * Step 1:
 * Build prefix tree
 *
 * Step 2:
 * DFS find all valid word squares
 *
 *
 * TODO:
 * Optimize space complexity
 */
public class WordSquares {

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> results = new ArrayList<>();

        if (words == null || words.length == 0) return results;

        PrefixTrie prefixTrie = new PrefixTrie();
        prefixTrie.add(words);

        for (String word: words) {
            List<String> candidate = new ArrayList<>();
            candidate.add(word);
            dfs(prefixTrie, results, candidate, word.length(), 1);
        }

        return results;
    }

    private void dfs(PrefixTrie prefixTrie, List<List<String>> results, List<String> candidate, int len, int index) {
        if (index == len) {
            results.add(new ArrayList<>(candidate));
            return;
        }

        StringBuilder prefix = new StringBuilder();

        for (String s: candidate) {
            prefix.append(s.charAt(index));
        }

        Set<String> nextOptions = prefixTrie.startWith(prefix.toString());

        for (String option: nextOptions) {
            candidate.add(option);
            dfs(prefixTrie, results, candidate, len, index + 1);
            candidate.remove(candidate.size() - 1);
        }
    }

    class PrefixTrie {
        TrieNode root;

        PrefixTrie() {
            root = new TrieNode();
        }

        void add(String word) {
            TrieNode cur = root;

            for (char c: word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }

                cur.children[index].words.add(word);
                cur = cur.children[index];
            }
        }

        void add(String[] words) {
            for (String word: words) {
                this.add(word);
            }
        }

        Set<String> startWith(String prefix) {
            TrieNode cur = root;
            for (char c: prefix.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) return new HashSet<>();
                cur = cur.children[index];
            }

            return cur.words;
        }
    }

    class TrieNode {
        TrieNode[] children;
        Set<String> words;

        TrieNode() {
            children = new TrieNode[26];
            words = new HashSet<>();
        }
    }
}
