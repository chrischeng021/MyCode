package com.leetcode;

import com.leetcode.model.TreeNode;

public class TestHelper {
    public static TreeNode createBinaryTree(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = null;
        root.right.right = new TreeNode(6);

        return root;
    }
}