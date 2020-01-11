package string;

/**
 * 273:
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {
    private static final String[] unitOne = new String[] {
            "", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen", "Twenty"
    };

    private static final String[] unitTen = new String[] {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return helper(num).trim();
    }

    public String helper(int num) {
        if (num >= 1000000000) return helper(num / 1000000000).trim() + " Billion " + helper(num % 1000000000);
        if (num >= 1000000) return helper(num / 1000000).trim() + " Million " + helper(num % 1000000);
        if (num >= 1000) return helper(num / 1000).trim() + " Thousand " + helper(num % 1000);
        if (num >= 100) return unitOne[num/100] + " Hundred " + helper(num % 100);
        if (num >= 20) return unitTen[num/10] + " " + unitOne[num % 10];
        return unitOne[num];
    }
}
