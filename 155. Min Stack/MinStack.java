import java.util.Stack;

public class MinStack {

    private int min;
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            minStack.push(x);
            min = x;
        } else {
            if (x <= min) {
                minStack.push(x);
                min = x;
            }
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int v = stack.pop();
        if (v == min) {
            if (!minStack.isEmpty()) {
                minStack.pop();
            }
            if (!minStack.isEmpty()) {
                min = minStack.peek();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public int getMin() {
        return min;
    }
}
