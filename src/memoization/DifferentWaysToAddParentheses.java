package memoization;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        return waysHelper(input);
    }

    private List<Integer> waysHelper(String s) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < 3) return list;

        if (s.length() == 3) {
            int left = s.charAt(0) - 48;
            int right = s.charAt(2) - 48;
            char op = s.charAt(1);
            list.add(calc(op, left, right));
        }

        for (int i = 0; i < s.length() - 1; i+=2) {
            List<Integer> rightList = waysHelper(s.substring(i + 2));
            int leftNum = s.charAt(i) - 48;
            char op = s.charAt(i + 1);

            for (int rightNum : rightList) {
                System.out.println(leftNum + " : " + rightNum);
                list.add(calc(op, leftNum, rightNum));
            }
        }

        return list;
    }

    private int calc(char op, int num1, int num2) {
        if (op == '+') return num1 + num2;
        if (op == '-') return num1 - num2;
        return num1 * num2;
    }
}
