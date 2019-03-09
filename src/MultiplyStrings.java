/**
 * LeetCode 43:
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2,
 * also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 */

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int x = num1.charAt(i) - '0';
                int y = num2.charAt(j) - '0';
                int lowDigitIndex = i + j + 1;
                int highDigitIndex = i + j;
                int prod = res[lowDigitIndex] + x * y;

                res[lowDigitIndex] = prod % 10;
                res[highDigitIndex] += prod / 10;
            }
        }

        int i = 0;

        while (i < res.length && res[i] == 0) i++;

        StringBuilder sb = new StringBuilder();

        while (i < res.length) sb.append(res[i++]);

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
