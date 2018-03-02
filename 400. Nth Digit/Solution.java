public class Solution {

    public int findNthDigit(int n) {
        int start = 1;
        int len = 1;
        long base = 9;
        while (n > len * base) {
            n = n - len * (int) base;
            len++;
            start *= 10;
            base *= 10;
        }
        int target = start + (n - 1) / len;
        int position = (n - 1) % len;
        return Character.getNumericValue(Integer.toString(target).charAt(position));
    }
}
