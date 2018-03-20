import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        restore(s, 4, "", res);
        return res;
    }

    private void restore(String s, int k, String o, List<String> res) {
        if (k == 0) {
            if (s.isEmpty()) {
                res.add(o);
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                if (s.length() >= i && isValid(s.substring(0, i))) {
                    if (k == 1) {
                        restore(s.substring(i), k - 1, o + s.substring(0, i), res);
                    } else {
                        restore(s.substring(i), k - 1, o + s.substring(0, i) + ".", res);
                    }
                }
            }
        }
    }

    private boolean isValid(String s) {
        if (s.isEmpty() || s.length() > 3 || (s.length() > 1 && s.charAt(0) == '0')) {
            return false;
        }
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
}