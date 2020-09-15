package com.leetcode.middle;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for(String token : tokens){
            switch(token){
                case "+":{
                    if(stack.size() < 2) return 0;
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf((a + b)));
                    break;
                }
                case "-":{
                    if(stack.size() < 2) return 0;
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf((a - b)));
                    break;
                    }
                case "*":{
                    if(stack.size() < 2) return 0;
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf((a * b)));
                    break;
                }
                case "/":{
                    if(stack.size() < 2) return 0;
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf((a * b)));
                    break;
                }
                default:{
                    stack.push(token);
                    break;
                }
            }
        }
        return stack.size() == 1 ? Integer.parseInt(stack.pop()) : 0;
    }
}
