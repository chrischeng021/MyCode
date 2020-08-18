package com.leetcode.easy;

public class AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int caps = 0;
        for(int i = 1;;i++){
            int indexB = b.length() - i;
            int indexA = a.length() - i;

            if(indexA < 0 && indexB < 0 && caps == 0){
                break;
            }

            char chA = '0';
            char chB = '0';

            if(indexB >= 0){
                chB = b.charAt(indexB);
            }

            if(indexA >= 0){
                chA = a.charAt(indexA);
            }

            if(chA == '0' && chB == '0'){
                sb.append(caps == 0 ? "0" : "1");
                caps = 0;
                continue;
            }
            if(chA == '1' && chB == '1'){
                sb.append(caps == 0 ? "0" : "1");
                caps = 1;
                continue;
            }
            if(caps == 1){
                sb.append("0");
            }
            else{
                sb.append("1");
            }
        }
        return sb.reverse().toString();
    }
}