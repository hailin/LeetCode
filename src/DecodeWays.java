import java.util.HashMap;
import java.util.Map;

public class DecodeWays {


    // Recursion with memoization
    public int numDecodingsI(String s) {
        Map<String, Integer> mem = new HashMap<>();
        return countWays(s, mem);
    }

    private int countWays(String s, Map<String, Integer> mem) {
        if (s.length() == 0) return 1;
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        if (mem.containsKey(s)) return mem.get(s);

        int count1 = countWays(s.substring(1), mem);
        mem.put(s.substring(1), count1);


        int twoDigit = Integer.valueOf(s.substring(0, 2));

        int count2 = twoDigit <= 0 || twoDigit > 26 ? 0 : countWays(s.substring(2), mem);
        mem.put(s.substring(2), count2);

        return count1 + count2;
    }
}
