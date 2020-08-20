package com.leetcode.middle;

// 让我们一起来玩扫雷游戏！
// 给定一个代表游戏板的二维字符矩阵。 
// 'M' 代表一个未挖出的地雷，
// 'E' 代表一个未挖出的空方块，
// 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块.
// 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
// 1. 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
// 2. 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
// 3. 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
// 4. 如果在此次点击中，若无更多方块可被揭露，则返回面板。

// 链接：https://leetcode-cn.com/problems/minesweeper
// TODO: 本题有DFS和BFS两种解法
public class MineSweeper {
    int[] dx = {0, 0, 1,  1, 1, -1,-1, -1};
    int[] dy = {1,-1, 0, -1, 1,  0, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
        }
        else{
            dfsTraverse(board, x, y);
        }
        return board;
    }
    private void dfsTraverse(char[][] board, int x, int y){
        int mineCount = 0;
        for(int i = 0; i < 8; i++){
            int m = x + dx[i];
            int n = y + dy[i];
            if(m >= 0 && m < board.length && n >= 0 && n < board[0].length && board[m][n] == 'M'){
                mineCount++;
            }
        }
        // 如果(x, y)位置有相邻的地雷，遵循规则3
        if(mineCount > 0){
            board[x][y] = (char)(mineCount + '0');
            return;
        }
        board[x][y] = 'B';
        // 如果(x, y)位置没有相邻的地雷，亦即，其上下左右对角线的八个相邻位置全部为‘E’，则修改(x, y)值为B，并递归的遍历其相邻的八个位置
        for(int i = 0; i < 8; i++){
            int m = x + dx[i];
            int n = y + dy[i];
            // 注意规则4，递归时需要注意确保待挖掘坐标尚未被揭露，否则会导致递归爆栈。
            if(m >= 0 && m < board.length && n >= 0 && n < board[0].length && board[m][n] == 'E'){
                dfsTraverse(board, m, n);
            }
        }
    }
}