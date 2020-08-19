package com.leetcode.model;

// 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
// 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
// TODO: 需要复习，代码是参考题解才写出的。
public class TrieTree {
    class TrieTreeNode{
        TrieTreeNode [] subTreeNodes;
        boolean isEnd;

        public TrieTreeNode(){
            subTreeNodes = new TrieTreeNode[26];
        }
        public boolean containsKey(char ch) {
            return subTreeNodes[ch -'a'] != null;
        }
        public TrieTreeNode get(char ch) {
            return subTreeNodes[ch -'a'];
        }
        public void put(char ch, TrieTreeNode node) {
            subTreeNodes[ch -'a'] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
    TrieTreeNode root;

    /** Initialize your data structure here. */
    public TrieTree() {
        root = new TrieTreeNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieTreeNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieTreeNode searchPrefix(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } 
            else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieTreeNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieTreeNode node = searchPrefix(prefix);
        return node != null;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */