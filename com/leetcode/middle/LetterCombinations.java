package com.leetcode.middle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
// 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinations {
    static class TrieNode{
        public List<TrieNode> trieNodes;
        String val;

        public TrieNode(){
            trieNodes = new LinkedList<TrieNode>();
            val = "";
        }

        public TrieNode(TrieNode root, char ch){
            trieNodes = new LinkedList<TrieNode>();
            val = root.val + String.valueOf(ch);
        }

        public void addNodes(TrieNode root,String str){
            for(int i = 0; i < str.length(); i++){
                TrieNode node = new TrieNode(root, str.charAt(i));
                this.trieNodes.add(node);
            }
        }
    }
    public static List<String> letterCombinations(String digits) {
        List<String> ans = new LinkedList<>();
        if(digits == null || digits.length() == 0){
            return ans;
        }
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        TrieNode root = new TrieNode();
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        for(int i = 0; i < digits.length(); i++){
            char ch = digits.charAt(i);
            if(!Character.isDigit(ch) || ch == '0' || ch == '1'){
                continue;
            }
            String str = map[ch - '0' - 2];
            int len = queue.size();
            for(int j = 0; j < len; j++){
                TrieNode node = queue.poll();
                node.addNodes(node, str);
                queue.addAll(node.trieNodes);
            }
        }
        traverseTrie(root, ans);
        return ans;
    }

    private static void traverseTrie(TrieNode root, List<String> ans){
        if(root.trieNodes == null || root.trieNodes.size() == 0){
            ans.add(root.val);
        }
        
        for(TrieNode node : root.trieNodes){
            traverseTrie(node, ans);
        }
    }
}