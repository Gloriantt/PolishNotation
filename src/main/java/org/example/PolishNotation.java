package org.example;

import java.util.Stack;
public class PolishNotation {
    public static String toPolishNotation(String expression) {
        try {
            String[] tokens = expression.split(" ");
            Stack<String> stack = new Stack<String>();
            StringBuilder output = new StringBuilder();
            for (String token : tokens) {
                if (isOperator(token)) {
                    while (!stack.isEmpty() && isOperator(stack.peek())) {
                        if (getPrecedence(token) <= getPrecedence(stack.peek())) {
                            output.append(stack.pop()).append("");
                            continue;
                        }
                        break;
                    }
                    stack.push(token);
                } else if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        output.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                } else {
                    output.append(token).append(" ");
                }
            }


            while (!stack.isEmpty()) {
                output.append(stack.pop()).append(" ");
            }

            return output.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static double calculate(String expression) {
        try {
            String[] tokens = expression.split(" ");
            Stack<Double> stack = new Stack<Double>();
            for (String token : tokens) {
                if (isOperator(token)) {
                    double operand2;
                    double operand1;
                    switch (token) {
                        case "+":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            stack.push(operand1 + operand2);
                            break;
                        case "-":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            stack.push(operand1 - operand2);
                            break;
                        case "*":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            stack.push(operand1 * operand2);
                            break;
                        case "/":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            stack.push(operand1 / operand2);
                            break;
                        case "^":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            stack.push(Math.pow(operand1, operand2));
                            break;
                        case "log":
                            operand2 = stack.pop();
                            stack.push(Math.log10(operand2));
                            break;
                        case "sqrt":
                            operand2 = stack.pop();
                            stack.push(Math.sqrt(operand2));
                            break;
                        case "e":
                            operand2 = stack.pop();
                            stack.push(Math.exp(operand2));
                            break;
                    }
                } else {
                    stack.push(Double.parseDouble(token));
                }
            }
            return stack.pop();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean isOperator(String token) {
        if (token.equals("e") || token.equals("sqrt") || token.equals("log") || token.equals("+")
                || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")){
            return true;
        }else if( token.equals(" ")){
            return false;}
        return false;
    }

    private static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "log":
            case "sqrt":
            case "e":
                return 2;
            case "^":
                return 4;
        }
        return 0;
    }

}