package application.tratandoeventos;

import java.util.LinkedList;

public class Queue {
    private LinkedList<String> list = new LinkedList<>();

    public void enqueue(String value) {
        list.addLast(value);
    }

    public String dequeue() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }
}
