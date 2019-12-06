package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) return Collections.singletonList(1);

        if (rowIndex == 1) return Arrays.asList(1, 1);

        return Arrays.asList(getRow(rowIndex, new Integer[]{1, 1}, 2));
    }

    private Integer[] getRow(int rowIndex,  Integer[] prevRow, int curRowIndex) {
        Integer[] curRow = new Integer[curRowIndex + 1];
        curRow[0] = 1;
        curRow[curRow.length - 1] = 1;

        for (int i = 1; i < curRow.length - 1; i++) {
            curRow[i] = prevRow[i - 1] + prevRow[i];
        }

        if (curRowIndex == rowIndex) return curRow;

        return getRow(rowIndex, curRow, curRowIndex + 1);
    }
}
