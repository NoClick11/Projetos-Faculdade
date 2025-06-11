package application.tratandoeventos;

import java.util.LinkedList;

public class Stack {
    private LinkedList<Double> list = new LinkedList<>();

    public void push(double value) {
        list.addFirst(value);
    }

    public double pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }
}
