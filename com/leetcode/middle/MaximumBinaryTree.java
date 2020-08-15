package com.leetcode.middle;

import com.leetcode.model.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MaximumBinaryTree {
    static TreeNode ans = null;

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        if(nums.length == 1){
            return new TreeNode(nums[0]);
        }
        generateMaxBinaryTree(nums, 0, nums.length - 1);
        return ans;
    }

    private static TreeNode generateMaxBinaryTree(int[] nums, int l, int r){
        if(l > r){
            return null;
        }
        if(l == r){
            return new TreeNode(nums[l]);
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;

        for(int i = l; i <= r; i++){
            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        if(ans == null){
            ans = root;
        }
        root.left = generateMaxBinaryTree(nums, l, maxIndex - 1);
        root.right = generateMaxBinaryTree(nums, maxIndex + 1, r);

        return root;
    }
}