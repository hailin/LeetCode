package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) return list;

        Map<Character, String> map = new HashMap<>();
        map.put('0', "");
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(map, list, digits, "");
        return list;
    }

    private void backtrack(Map<Character, String> map, List<String> list, String digits, String cur) {
        if (cur.length() == digits.length()) {
            list.add(cur);
            return;
        }

        int nextIndex = cur.length();
        String nextLetters = map.get(digits.charAt(nextIndex));

        for (int i = 0; i < nextLetters.length(); i++) {
            char nextChar = nextLetters.charAt(i);
            backtrack(map, list, digits, cur + nextChar);
        }
    }
}
