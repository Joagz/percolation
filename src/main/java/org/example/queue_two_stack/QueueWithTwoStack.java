package org.example.queue_two_stack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class QueueWithTwoStack<Item> {

    private Stack<Item> in;
    private Stack<Item> out;

    public QueueWithTwoStack() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void enqueue(Item i) {
        in.push(i);
    }


    public Item dequeue() {
        if (out.size() != in.size())
            for (Item i : in) {
                out.push(i);
                in.pop();
            }

        return out.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : in){
            sb.append(i + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        QueueWithTwoStack<Integer> queue = new QueueWithTwoStack<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());

    }

}

