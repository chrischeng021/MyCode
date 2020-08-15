package com.leetcode.middle;

import java.util.LinkedList;
import java.util.List;

import com.leetcode.model.TreeNode;

// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
// 链接：https://leetcode-cn.com/problems/validate-binary-search-tree/
// TODO: 本题参考了题解，需要复习。
public class BSTUtils {
    long pre = Long.MIN_VALUE;
    TreeNode parentNode = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public List<Integer> traverseInOrder(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new LinkedList<>();
        traverseInOrder(root, res);
        return res;
    }

    private void traverseInOrder(TreeNode root, List<Integer> data){
        if(root == null){
            return;
        }
        traverseInOrder(root.left, data);
        data.add(root.val);
        traverseInOrder(root.right, data);
    }

    // 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
    // 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
    // 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
    // 使用原地插入的方法
    // 另一种思路是先中序遍历 生成有序数组后插入 再根据有序数组生成新的BST 但是该种方式相对而言效率较低
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode node = findTargetNode(root, val);
        if(node != null){
            System.out.println("Target Node = " + node.val);
            TreeNode newNode = new TreeNode(val);
            if(node.val > val){
                TreeNode l = node.left;
                node.left = newNode;
                if(l != null){
                    if(l.val < newNode.val){
                        newNode.left = l;
                    }
                    else{
                        newNode.right = l;
                    }
                }
            }
            else{
                TreeNode r = node.right;
                node.right = newNode;
                if(r != null){
                    if(r.val < newNode.val){
                        newNode.left = r;
                    }
                    else{
                        newNode.right = r;
                    }
                }
            }
        }
        return root;
    }
    private TreeNode findTargetNode(TreeNode root, int val){
        if(root.left == null && root.right == null){
            return root;
        }
        if(root.val < val){
            return root.right == null ? root : findTargetNode(root.right, val);
        }
        if(root.val > val){
            return root.left == null ? root : findTargetNode(root.left, val);
        }
        return null;
    }

    // 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
    // 一般来说，删除节点可分为两个步骤：
    // 首先找到需要删除的节点；
    // 如果找到了，删除它。
    // 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
    // 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode targetNode = finNode(root, key);
        if(targetNode == null){
            return root;
        }
        // 若目标结点的左子树为空 则直接将父节点指向目标结点的右子树即可
        if(targetNode.left == null){
            // 目标结点为根节点
            if(parentNode == null){
                root = root.right;
                return root;
            }
            if(parentNode.val > targetNode.val){
                parentNode.left = targetNode.right;
            }
            else{
                parentNode.right = targetNode.right;
            }
            return root;
        }
        // 若目标结点的右子树为空 则直接将父节点指向目标结点的左子树即可
        if(targetNode.right == null){
            // 目标结点为根节点
            if(parentNode == null){
                root = root.left;
            }
            else{
                if(parentNode.val > targetNode.val){
                    parentNode.left = targetNode.left;
                }
                else{
                    parentNode.right = targetNode.left;
                }
            }
            return root;
        }
        // 将目标结点的左子树设置为目标结点右节点的最左叶子结点的左子树
        TreeNode rSubTree = targetNode.right;
        TreeNode lSubTree = targetNode.left;
        if(parentNode == null){
            root = root.right;
        }
        else{
            if(parentNode.val > targetNode.val){
                parentNode.left = rSubTree;
            }
            else{
                parentNode.right = rSubTree;
            }
        }
        while(rSubTree.left != null){
            rSubTree = rSubTree.left;
        }
        rSubTree.left = lSubTree;
        
        return root;
    }

    // 找到待删除结点，同时记录其父节点
    private TreeNode finNode(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val == key){
            return root;
        }
        if(root.val > key){
            parentNode = root.left;
            return finNode(root.left, key);
        }
        else{
            parentNode = root.right;
            return finNode(root.right, key);
        }
    }
}