import java.util.HashSet;
import java.util.Set;

public class ContinuousSubarraySum {

    /**
     *  Time: O(n) Space: O(n)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 && nums[i - 1] == 0) return true;
        }

        if (k == 0) return false;

        Set<Integer> comp = new HashSet<>();
        int mod = nums[0] % k;
        comp.add(mod);
        comp.add(0);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            mod = (mod + nums[i]) % k;
            if (comp.contains(mod)) return true;
            comp.add(mod);
        }

        return false;
    }

    /**
     *  Time: O(n^2) space: O(1)
     */
    public boolean checkSubarraySumII(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];

                if (sum == 0 && k == 0) return true;
                if (k == 0) return false;
                if (sum % k == 0) return true;
            }
        }

        return false;
    }
}
