package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 这里主要是使用深度优先搜索、回溯算法的题型
public class MyDFS {
    // 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    // 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    // 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
    // 链接：https://leetcode-cn.com/problems/restore-ip-addresses
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        if(s.length() < 4 || s.length() > 12){
            return ans;
        }
        dfsTraverseIpAddresses(s, 0, new LinkedList<>(), ans);
        return ans;
    }

    private static void dfsTraverseIpAddresses(String str, int startIndex, List<String> singleIpList, List<String> ans){
        if(singleIpList.size() == 4 && startIndex == str.length()){
            StringBuilder sb = new StringBuilder();
            for(String ip : singleIpList){
                sb.append(ip).append(".");
            }
            ans.add(sb.substring(0, sb.length() - 1).toString());
        }
        for(int len = 1; len < 4 && startIndex + len <= str.length() && singleIpList.size() < 4; len++){
            String val = str.substring(startIndex, startIndex + len);
            if(val.length() > 1 && (val.startsWith("0") || Integer.valueOf(val) > 255)){
                continue;
            }
            singleIpList.add(val);
            dfsTraverseIpAddresses(str, startIndex + len, new LinkedList<>(singleIpList), ans);
            singleIpList.remove(singleIpList.size() - 1);
        }
    }

    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    // 链接：https://leetcode-cn.com/problems/permutations/
    public static List<List<Integer>> permute(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        List<List<Integer>> ans = new LinkedList<>();
        dfsTraverseNums(new HashSet<>(set), new LinkedList<>(), ans);
        return ans;
    }
    private static void dfsTraverseNums(HashSet<Integer> set, List<Integer> list, List<List<Integer>> ans){
        if(set.isEmpty()){
            ans.add(list);
        }
        for(int num : set){
            list.add(num);
            HashSet<Integer> setRemovedNum = new HashSet<>(set);
            setRemovedNum.remove(num);
            dfsTraverseNums(setRemovedNum, new LinkedList<>(list), ans);
            // 所有的深度搜索+回溯 一定要加上这一步Remove操作
            list.remove(list.size() - 1);
        }
    }

    // 给定一个可包含重复数字的序列，返回所有不重复的全排列。
    // 链接：https://leetcode-cn.com/problems/permutations-ii/
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        for(int num : nums) list.add(num);
        dfsTraverseNums(new LinkedList<>(list), new LinkedList<>(), new HashSet<>(), ans);
        return ans;
    }

    private static void dfsTraverseNums(List<Integer> nums, List<Integer> list, HashSet<String> set, List<List<Integer>> ans){
        if(nums.size() == 0){
            StringBuilder sb = new StringBuilder();
            for(int num : list) sb.append(num);
            if(!set.contains(sb.toString())){
                ans.add(list);
                set.add(sb.toString());
            }
        }
        int added = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++){
            if(added != Integer.MIN_VALUE && nums.get(i) == added){
                continue;
            }
            added = nums.get(i);
            list.add(added);
            LinkedList<Integer> listRemovedNum = new LinkedList<>(nums);
            listRemovedNum.remove(i);
            dfsTraverseNums(listRemovedNum, new LinkedList<>(list), set, ans);
            list.remove(list.size() - 1);
        }
    }

    // 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    // 说明：解集不能包含重复的子集。
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        if(nums.length > 0){
            HashSet<Integer> set = new HashSet<>();
            for(int num : nums) set.add(num);
            ans.add(new LinkedList<>(set));
            dfsSubSets(new HashSet<>(set), ans);
        }
        ans.add(new LinkedList<>());
        return ans;
    }

    private static void dfsSubSets(HashSet<Integer> set, List<List<Integer>> ans){
        for(int num : set){
            HashSet<Integer> subSet = new HashSet<>(set);
            subSet.remove(num);
            if(subSet.isEmpty()) return;
            ans.add(new LinkedList<>(subSet));
            dfsSubSets(subSet, ans);
        }
    }
}