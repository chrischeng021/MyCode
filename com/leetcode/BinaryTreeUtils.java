package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    // 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
    // 链接：https://leetcode-cn.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        sum -= root.val;

        if(root.left == null && root.right == null){
            return sum == 0;
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    // 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
    // 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci/
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));

        return root;
    }

    // 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
    // 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    // 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
    // 备注：效率更高的解法思路为
    // 从根节点开始遍历树
    // 如果节点 pp 和节点 qq 都在右子树上，那么以右孩子为根节点继续 1 的操作
    // 如果节点 pp 和节点 qq 都在左子树上，那么以左孩子为根节点继续 1 的操作
    // 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 pp 和节点 qq 的 LCA 了
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }
        if(root.val <= Math.max(p.val, q.val) && root.val >= Math.min(p.val, q.val))
        {
            return root;
        }
        if(root.val <= Math.max(p.val, q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }
        else{
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}