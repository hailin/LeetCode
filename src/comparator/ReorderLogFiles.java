package comparator;

import java.util.Arrays;

/**
 * 937:
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 *
 * Constraints:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] log1Parts = log1.split(" ", 2);
            String[] log2Parts = log2.split(" ", 2);
            char c1 = log1Parts[1].charAt(0);
            char c2 = log2Parts[1].charAt(0);
            boolean letterLogs = Character.isLetter(c1) && Character.isLetter(c2);
            boolean identicalLog = log1Parts[1].equals(log2Parts[1]);

            if (letterLogs) {
                return identicalLog
                        ? log1Parts[0].compareTo(log2Parts[0])
                        : log1Parts[1].compareTo(log2Parts[1]);
            }

            boolean digitLogs = Character.isDigit(c1) && Character.isDigit(c2);

            if (digitLogs) return 0;

            return Character.isLetter(c1) ? -1 : 1;
        });

        return logs;
    }
}
