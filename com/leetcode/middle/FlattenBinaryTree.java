package com.leetcode.middle;

import com.leetcode.model.TreeNode;

// 给定一个二叉树，原地将它展开为一个单链表。
// 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
// TODO: Discuss中有较多更多效率更高的解法 可以review
public class FlattenBinaryTree {
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
                continue;
            }
            TreeNode leftSubTreeRoot = root.left;
            while(leftSubTreeRoot.right != null){
                leftSubTreeRoot = leftSubTreeRoot.right;
            }
            leftSubTreeRoot.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }
}