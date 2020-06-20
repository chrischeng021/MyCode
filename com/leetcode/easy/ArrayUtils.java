package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
    // 给你一个以行程长度编码压缩的整数列表 nums 。
    // 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >=
    // 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
    // 请你返回解压后的列表。
    // 链接：https://leetcode-cn.com/problems/decompress-run-length-encoded-list
    public int[] decompressRLElist(int[] nums) {
        int resSize = 0;

        for (int i = 0; (i * 2 + 1) < nums.length; i++) {
            resSize += nums[2 * i];
        }

        int[] res = new int[resSize];
        int index = 0;
        for (int i = 0; (i * 2 + 1) < nums.length; i++) {
            int frequence = nums[2 * i];
            int val = nums[2 * i + 1];
            for (; frequence > 0; index++, frequence--) {
                res[index] = val;
            }
        }

        return res;
    }

    // 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
    // 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
    // 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i],
    // endTime[i]]（含）的学生人数。
    // 链接：https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ret = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ret++;
            }
        }
        return ret;
    }
}