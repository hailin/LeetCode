package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 118:
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        List<Integer> rowOne = Collections.singletonList(1);
        result.add(rowOne);
        generate(result, numRows, new Integer[]{1}, 2);
        return result;
    }

    private void generate(List<List<Integer>> result, int maxRow, Integer[] prevRow, int rowNum) {
        if (rowNum > maxRow) return;

        Integer[] curRow = new Integer[rowNum];
        curRow[0] = 1;
        curRow[rowNum - 1] = 1;

        for (int i = 1; i < curRow.length - 1; i++) {
            curRow[i] = prevRow[i - 1] + prevRow[i];
        }
        List<Integer> list = Arrays.asList(curRow);
        result.add(list);
        generate(result, maxRow, curRow, rowNum + 1);
    }
}
