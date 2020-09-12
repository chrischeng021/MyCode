package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.model.TreeNode;
/* cSpell:disable */
public class MyBinaryTreeUtils {
    // 输入一棵二叉树的根节点，求该树的深度。
    // 从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
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

    // 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
    // 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        traverseBinaraySearchTree(root, list);
        return list.get(k - 1);
    }

    // 给定一个二叉搜索树，编写一个函数 kthLargest 来查找其中第 k 个最大的元素。
    // 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        traverseBinaraySearchTree(root, list);
        return list.get(list.size() - k);
    }

    private void traverseBinaraySearchTree(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        traverseBinaraySearchTree(root.left, list);
        list.add(root.val);
        traverseBinaraySearchTree(root.right, list);
    }

    // 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树
    // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int from, int to){
        List<TreeNode> list = new ArrayList<>();
        if(from > to){
            list.add(null);
            return list;
        }
        for(int i = from; i <= to; i++){
            List<TreeNode> lTrees = generateTrees(from, i - 1);
            List<TreeNode> rTrees = generateTrees(i + 1, to);

            for (TreeNode left : lTrees) {
                for (TreeNode right : rTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    list.add(currTree);
                }
            }
        }
        return list;
    }

    // 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    // 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return res;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                subList.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(subList);
        }
        return res;
    }

    //实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
    // 链接：
    public boolean isBalanced(TreeNode root) {
        return isBalancedDFSTraverse(root);
    }
    private boolean isBalancedDFSTraverse(TreeNode root){
        if(root == null){
            return true;
        }
        if(Math.abs(getHeight(root.left) - getHeight(root.right)) > 1){
            return false;
        }
        return isBalancedDFSTraverse(root.left) && isBalancedDFSTraverse(root.right);
    }
    private int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    // 根据给定数组生成二叉搜索树
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length == 0){
            return null;
        }
        Arrays.sort(preorder);
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }
    private TreeNode bstFromPreorder(int[] preorder, int l, int r){
        if(l < r){
            int mid = (l + r)/2;
            TreeNode root = new TreeNode(preorder[mid]);
            root.left = bstFromPreorder(preorder, l, mid - 1);
            root.right = bstFromPreorder(preorder, mid + 1, r);
            return root;
        }
        else if(l == r){
            return new TreeNode(preorder[l]);
        }
        else{
            return null;
        }
    }
    // 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
    // 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
    // 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null){
            return -1;
        }
        return findFirstDifferent(root, root.val);
    }
    private int findFirstDifferent(TreeNode root, int val){
        if(root == null){
            return -1;
        }
        if(root.val != val){
            return root.val;
        }
        if(root.left == null){
            return -1;
        }
        int right = findFirstDifferent(root.right, val);
        int left = findFirstDifferent(root.left, val);
        return left == -1 ? right : right == -1 ? left : Math.min(left, right);
    }

    // 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
    // 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }

        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        while(!list.isEmpty()){
            int len = list.size();
            int[] arr = new int[len];
            for(int i = 0; i < len; i++){
                TreeNode node = list.poll();
                arr[i] = node == null ? Integer.MIN_VALUE : node.val;
                if(node != null){
                    list.add(node.left);
                    list.add(node.right);
                }
                
            }
            for(int i = 0, j = len - 1; i < j; i++, j--){
                if(arr[i] != arr[j]){
                    return false;
                }
            }
        }

        return true;
    }

    // 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
    // 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    // 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
    // TODO：效率更高的解法思路为
    // 从根节点开始遍历树
    // 如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
    // 如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
    // 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 pp 和节点 qq 的 LCA 了
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }
        if(root.val <= Math.max(p.val, q.val) && root.val >= Math.min(p.val, q.val))
        {
            return root;
        }
        if(root.val <= Math.max(p.val, q.val)){
            return lowestCommonAncestorI(root.right, p, q);
        }
        else{
            return lowestCommonAncestorI(root.left, p, q);
        }
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }
        if(root.val > Math.max(p.val, q.val)){
            return lowestCommonAncestorII(root.left, p, q);
        }
        else if(root.val < Math.min(p.val, q.val)){
            return lowestCommonAncestorII(root.right, p, q);
        }
        else{
            return root;
        }
    }

    // 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    // 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xopaih/
    // 注意：本题为二叉树的公共祖先，而非二叉搜索树的公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    // 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
    // B是A的子结构， 即 A中有出现和B相同的结构和节点值。
    // https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if( A == null || B == null) return false;
        return isSubTreeSubStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubTreeSubStructure(TreeNode A, TreeNode B){
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return isSubTreeSubStructure(A.left, B.left) && isSubTreeSubStructure(A.right, B.right);
    }

    // 给定一个二叉树，返回所有从根节点到叶子节点的路径。
    // 链接：https://leetcode-cn.com/problems/binary-tree-paths/
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new LinkedList<>();
        if(root == null) return ans;
        if(root.left == null && root.right == null){
            ans.add(String.valueOf(root.val));
            return ans;
        }
        dfsTraverseBinaryTree(root, ans, new LinkedList<>());
        return ans;
    }
    private void dfsTraverseBinaryTree(TreeNode root, List<String> ans, List<Integer> path){
        if(root.left == null && root.right == null){
            StringBuilder sb = new StringBuilder();
            for(int val : path) sb.append(val).append("->");
            sb.append(root.val);
            ans.add(sb.toString());
            return;
        }
        path.add(root.val);
        if(root.left != null)
            dfsTraverseBinaryTree(root.left, ans, new LinkedList<>(path));
        if(root.right != null)
            dfsTraverseBinaryTree(root.right, ans, new LinkedList<>(path));
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.left == null && root.right == null) return root.val < max && root.val > min;
        if(root.left != null && root.val <= root.left.val) return false;
        if(root.right != null && root.val >= root.right.val) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    // 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
    // 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
    static class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    public static List<Node> preOrderList = new LinkedList<>();
    public static Node treeToDoublyListI(Node root) {
        if(root == null || (root.left == null && root.right == null)) return root;
        dfsTraversePreOrder(root);
        preOrderList.get(0).left = preOrderList.get(preOrderList.size() - 1);
        preOrderList.get(0).right = preOrderList.get(1);

        preOrderList.get(preOrderList.size() - 1).right = preOrderList.get(0);
        preOrderList.get(preOrderList.size() - 1).left = preOrderList.get(preOrderList.size() - 2);

        for(int i = 1; i < preOrderList.size() - 1; i++){
            preOrderList.get(i).left = preOrderList.get(i - 1);
            preOrderList.get(i).right = preOrderList.get(i + 1);
        }

        return preOrderList.get(0);
    }
    private static void dfsTraversePreOrder(Node root){
        if(root == null) return;
        dfsTraversePreOrder(root.left);
        preOrderList.add(root);
        dfsTraversePreOrder(root.right);
    }
    public static void test(){
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        Node head = treeToDoublyListI(root);
        System.out.println(head.val);
    }
    // 另一种原地修改的办法 使用一个前置结点指针
    Node pre, head;
    public Node treeToDoublyListII(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(Node cur) {
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    // 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    // 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int flag = 1;
        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> cur = new LinkedList<>();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                if(node != null){
                    if(flag == -1) {
                        cur.add(0, node.val);
                    }
                    else{
                        cur.add(node.val);
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if(cur.size() > 0){
                ans.add(cur);
            }
            flag *= -1;
        }
        return ans;
    }
}