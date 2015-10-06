package utils;

import java.util.Stack;

public class StackBuilder<K> {
    private final Stack<K> stack = new Stack<>();

    public StackBuilder push(K item) {
        stack.push(item);
        return this;
    }

    public Stack<K> build() {
        return stack;
    }

    public static StackBuilder stackBuilder() {
        return new StackBuilder();
    }
}
