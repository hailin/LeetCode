package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 93:
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class StoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;

        restoreHelper(result, s, "", 0);
        return result;
    }

    private void restoreHelper(List<String> result, String s, String cur, int sectionCount) {
        if (s.length() == 0 || sectionCount == 4) {
            if (s.length() == 0 && sectionCount == 4) result.add(cur);
            return;
        }

        String newCur = sectionCount > 0 ? cur + "." : cur;

        restoreHelper(result, s.substring(1), newCur + s.substring(0, 1), sectionCount + 1);

        if (s.charAt(0) != '0' && s.length() >= 2) {
            restoreHelper(result, s.substring(2), newCur + s.substring(0, 2), sectionCount + 1);
        }

        if (s.charAt(0) != '0' && s.length() >= 3 && Integer.parseInt(s.substring(0, 3)) <= 255) {
            restoreHelper(result, s.substring(3), newCur + s.substring(0, 3), sectionCount + 1);
        }
    }
}
