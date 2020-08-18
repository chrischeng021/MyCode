package com.leetcode.middle;

public class MultiplyStrings {
    // 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    // 链接：https://leetcode-cn.com/problems/multiply-strings/
    public static String multiply(String num1, String num2) {
        String base = "0";
        if(num1.equals("0") || num2.equals("0")){
            return base;
        }
        for(int i = num2.length() - 1; i >= 0; i--){
            if(num2.charAt(i) == '0'){
                continue;
            }
            StringBuilder str = new StringBuilder().append(multiplyStringWithChar(num1, num2.charAt(i)));
            for(int j = 0; j < (num2.length() - 1 - i); j++){
               str.append("0");
            }
            base = addStrings(base, str.toString());
        }
        return base;
    }

    public static String multiplyStringWithChar(String str, char ch){
        if(ch == '0'){
            return "0";
        }
        str = new StringBuilder().append(str).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int cap = 0;
        for(int i = 0; i < str.length(); i++){
            int res = (ch - '0') * (str.charAt(i) - '0') + cap;
            cap = res > 9 ? res/10 : 0;
            res -= cap * 10;
            sb.append(res);
        }
        if(cap > 0){
            sb.append(cap);
        }
        return sb.reverse().toString();
    }

    // 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
    // 链接：https://leetcode-cn.com/problems/add-strings/
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        num1 = new StringBuilder().append(num1).reverse().toString();
        num2 = new StringBuilder().append(num2).reverse().toString();
        int len = Math.min(num1.length(), num2.length());

        int cap = 0;
        int index = 0;
        
        for(; index < len; index++){
            int sum = num1.charAt(index) - '0' + num2.charAt(index) - '0' + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            sb.append(sum);
        }

        for(int i = index; i < num1.length(); i++){
            int sum = num1.charAt(i) - '0' + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            sb.append(sum);
        }

        for(int i = index; i < num2.length(); i++){
            int sum = num2.charAt(i) - '0' + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            sb.append(sum);
        }

        if(cap > 0){
            sb.append(cap);
        }

        return sb.reverse().toString();
    }
}