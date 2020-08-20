package com.leetcode.middle;

import java.util.Arrays;
// 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
// 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
// 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
// 链接：https://leetcode-cn.com/problems/matchsticks-to-square
// TODO
/* cSpell:disable */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if( sum % 4 != 0){
            return false;
        }
        int len = sum / 4;
        Arrays.sort(nums);
        int r = nums.length - 1;
        int count = 0;

        return false;
    }
}