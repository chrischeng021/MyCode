package com.leetcode.middle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.model.TreeNode;

public class BinaryTreePreOrderTraverse {
    // 给定一个二叉树，返回它的 前序 遍历。
    // 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    // 递归解法
    public List<Integer> preorderTraversalI(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversePreOrderDFS(root, list);
        return list;
    }

    private void traversePreOrderDFS(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        list.add(root.val);
        traversePreOrderDFS(root.left, list);
        traversePreOrderDFS(root.right, list);
    }

    // 迭代算法
    public static List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.add(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.right != null){
                    queue.add(0, node.right);
                }
                if(node.left != null){
                    queue.add(0, node.left);
                }
            }
        }
        return list;
    }
}