package com.leetcode.easy;

/*
 * 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1
 * 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 */
public class Steps2Reduce2Zero {
    public int numberOfSteps (int num) {
        int steps = 0;
        while(num > 0)
        {
            if(num%2 == 0)
            {
                num = num >> 1;
            }
            else
            {
                num -= 1;
            }
            steps++;
        }
        return steps;
    }
}