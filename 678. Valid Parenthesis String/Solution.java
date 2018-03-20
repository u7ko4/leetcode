import java.util.Stack;

public class Solution {

    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (left.isEmpty() && star.isEmpty()) {
                    return false;
                }
                if (!left.isEmpty()) {
                    left.pop();
                } else {
                    star.pop();
                }
            }
        }
        while (!left.isEmpty() && !star.isEmpty()) {
            if (left.peek() > star.peek()) return false;
            left.pop();
            star.pop();
        }
        return left.isEmpty();
    }
}