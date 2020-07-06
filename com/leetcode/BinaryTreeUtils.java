package com.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.model.TreeNode;

public class BinaryTreeUtils {
    // 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
    // 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        else{
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traverse(root, list);
        return list;
    }

    private void traverse(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            return;
        }
        if(root.left == null){
            list.add(root.right.val);
        }
        if(root.right == null){
            list.add(root.left.val);
        }

        traverse(root.left, list);
        traverse(root.right, list);
    }
}