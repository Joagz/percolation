package org.example.dijkstra;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ExpressionEvaluation {

    public static void main(String[] args) {

        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        System.out.println("Put an arithmetic expression, exit to quit.");

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("exit".equals(s) || "quit".equals(s)) {
                break;
            }
            switch (s) {
                case "(" -> {
                }
                case "+" -> operators.push("+");
                case "*" -> operators.push("*");
                case "-" -> operators.push("-");
                case "/" -> operators.push("/");
                case ")" -> {
                    String operator = operators.pop();
                    switch (operator) {
                        case "+" -> values.push(values.pop() + values.pop());
                        case "*" -> values.push(values.pop() * values.pop());
                        case "/" -> values.push(  (1 / values.pop()) * values.pop());
                        case "-" -> values.push(-values.pop() + values.pop());
                    }
                }
                default -> values.push(Double.parseDouble(s));
            }
        }
        StdOut.print(values.pop());

    }

}
