public class Solution {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int index = 0, sign = 1, total = 0;

        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }

        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        while (index < str.length()) {
            int num = str.charAt(index) - '0';
            if (num < 0 || num > 9) break;

            if (total > Integer.MAX_VALUE / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            if (total == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = 10 * total + num;
            index++;
        }

        return total * sign;
    }
}