package com.leetcode.middle;

import com.leetcode.model.TreeNode;

public class BuildBinaryTreeFromPreAndInOrder {
    public static int rootPreOrderIndex = 0;
    // 根据一棵树的前序遍历与中序遍历构造二叉树。
    // 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    // 不知道为什么这段代码本地运行可以，在线运行也可以，但是同样的case提交就是不过。
    // TODO: Need Review.
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        if(preorder.length == 1){
            return new TreeNode(preorder[0]);
        }
        
        return buildBinaryTreeCursive(preorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildBinaryTreeCursive(int[] preorder, int[] inorder, int inStart, int inEnd){
        if(inEnd >= inorder.length || inStart < 0 || rootPreOrderIndex >= preorder.length){
            return null;
        }
        if(inStart == inEnd && inEnd < inorder.length){
            rootPreOrderIndex++;
            return new TreeNode(inorder[inStart]);
        }
        TreeNode root = new TreeNode(preorder[rootPreOrderIndex++]);
        int rootInOrderIndex = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == root.val){
                rootInOrderIndex = i;
                break;
            }
        }
        root.left = buildBinaryTreeCursive(preorder, inorder, inStart, rootInOrderIndex - 1);
        root.right = buildBinaryTreeCursive(preorder, inorder, rootInOrderIndex + 1, inEnd);
        return root;
    }
}