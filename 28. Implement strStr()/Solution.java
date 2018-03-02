public class Solution {

    public int strStr(String haystack, String needle) {
        int n = needle.length();
        if (n == 0) {
            return 0;
        }
        int m = haystack.length();
        if (m < n) {
            return -1;
        }
        for (int i = 0; i <= m - n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        return -1;
    }
}
