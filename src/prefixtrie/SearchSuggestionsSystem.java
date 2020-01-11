package prefixtrie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1268:
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {
//    public static void main(String[] args) {
//        String[] products = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
//        List<List<String>> answer = new SearchSuggestionsSystem().suggestedProducts(products, "mouse");
//    }

    /**
     * TODO, use binary search
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        return result;
    }

        /**
         * Prefix Trie
         */
    public List<List<String>> suggestedProductsII(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        if (searchWord == null || searchWord.length() == 0 || products == null || products.length == 0) {
            return result;
        }

        TrieNode root = buildPrefixTrie(products);

        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i + 1);
            List<String> list = search(root, prefix);
            result.add(list);
        }

        return result;
    }

    private List<String> search(TrieNode root, String prefix) {
        TrieNode cur = root;
        List<String> list = new ArrayList<>();

        for (char c: prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) return list;
            cur = cur.children[c - 'a'];
        }

        search(list, cur);
        return list;
    }

    private void search(List<String> list, TrieNode cur) {
        if (list.size() >= 3) return;
        if (cur.isWord) list.add(cur.word);

        for (int i = 0; i < cur.children.length; i++) {
            TrieNode child = cur.children[i];
            if (child != null) {
                search(list, child);
            }
        }
    }

    private TrieNode buildPrefixTrie(String[] products) {
        TrieNode root = new TrieNode();

        for (String s: products) {
            TrieNode cur = root;
            for (char c: s.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }

                cur = cur.children[c - 'a'];
            }

            cur.word = s;
            cur.isWord = true;
        }

        return root;
    }

    private class TrieNode {
        String word;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }
}
