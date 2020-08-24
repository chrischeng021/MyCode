package com.leetcode.middle;

import com.leetcode.model.TreeNode;
// 根据一棵树的中序遍历与后序遍历构造二叉树。
// 注意:
// 你可以假设树中没有重复的元素。
// 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class BuildBinaryTreeFromInAndPostOrder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(
        int[] inorder, 
        int inStart, int inEnd,
        int[] postorder,
        int postStart, int postEnd){
        if(inorder.length == 0 || inorder.length != postorder.length ||
            inEnd < inStart || postEnd < postStart ||
            inEnd == inorder.length || postEnd == postorder.length){
            return null;
        }
        if(inorder.length == 1){
            return new TreeNode(inorder[0]);
        }
        if(inStart == inEnd){
            return new TreeNode(inorder[inStart]);
        }
        int val = postorder[postEnd];
        TreeNode root = new TreeNode(val);
        int rootIndex = -1;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == val){
                rootIndex = i;
                break;
            }
        }
        root.left = buildTree(inorder, inStart, rootIndex - 1, postorder, postStart, rootIndex - inStart + postStart - 1);
        root.right = buildTree(inorder, rootIndex + 1, inEnd, postorder, rootIndex - inStart + postStart, postEnd - 1);
        return root;
    }
}