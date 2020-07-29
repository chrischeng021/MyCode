package com.leetcode.middle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.leetcode.model.TreeNode;

// 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
// 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
// 链接：https://leetcode-cn.com/problems/most-frequent-subtree-sum
public class MostFrequentSubTreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        List<Integer> list = new LinkedList<>();
        int curMaxSumCount = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        calculateSubTreeSum(root, map);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() > curMaxSumCount){
                list.clear();
                curMaxSumCount = entry.getValue();
                list.add(entry.getKey());
                continue;
            }
            if(entry.getValue() == curMaxSumCount){
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private int calculateSubTreeSum(TreeNode root, HashMap<Integer, Integer> map){
        if(root == null){
            return 0;
        }
        int sum = root.val + calculateSubTreeSum(root.left, map) + calculateSubTreeSum(root.right, map);
        if(map.containsKey(sum)){
            map.put(sum, map.get(sum) + 1);
        }
        else{
            map.put(sum, 1);
        }
        return sum;
    }
}