import java.util.Stack;

public class Solution {

    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        boolean[] exist = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            int idx = c - 'a';
            cnt[idx]--;
            if (exist[idx]) {
                continue;
            }
            while (!stack.isEmpty() && c < stack.peek() && cnt[stack.peek() - 'a'] != 0) {
                exist[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            exist[idx] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}