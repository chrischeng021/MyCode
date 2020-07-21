package com.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

// 给定一个整数 n, 返回从 1 到 n 的字典顺序。
// 例如，
// 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
// 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
// 链接：https://leetcode-cn.com/problems/lexicographical-numbers
public class SortNumberByDicSequence {
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> list =  new ArrayList<>();
        generateDicSequenceNumber(list, -1, n);

        return list;
    }

    // TODO:该问题的思路还不够清楚，需要复习。
    public static void generateDicSequenceNumber(List<Integer> list, int curNum, int max){
        if(curNum > max){
            return;
        }
        if(curNum != -1){
            list.add(curNum);
        }

        for(int next = 0; next <=9; next++){
            if(curNum == -1){
                if(next == 0){
                    continue;
                }
                else{
                    curNum = 0;
                }
            }
            generateDicSequenceNumber(list, curNum * 10 + next, max);
        }
    }
}