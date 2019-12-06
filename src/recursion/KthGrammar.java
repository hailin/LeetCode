package recursion;

/**
 * 779:
 *
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 */
public class KthGrammar {

    public int kthGrammarII(int N, int K) {
        return kthGrammarII(N, K, new int[]{0}, 1);
    }

    private int kthGrammarII(int N, int K, int[] prevRow, int n) {
        int[] curRow = new int[(int) Math.pow(2, n)];

        for (int i = 0; i < curRow.length; i+=2) {
            int num = prevRow[i / 2];
            curRow[i] = num == 0 ? 0 : 1;
            curRow[i + 1] = num == 0 ? 1 : 0;
        }

        if (N == n) return curRow[K - 1];
        return kthGrammarII(N, K, curRow, n + 1);
    }
}
