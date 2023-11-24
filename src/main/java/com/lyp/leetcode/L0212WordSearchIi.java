package com.lyp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description 深度优先 212. 单词搜索 II
 * @date 2023/11/24 19:37
 * @author liyapeng
 */
public class L0212WordSearchIi{
  // 所有方向
  int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  // 是否已经访问过
  boolean[][] visited;

  // 矩阵的长与宽
  int m = 0;

  int n = 0;

  // 这里不要用list，list的contains与remove效率都比较低
  Set<String> words;

  char[][] board;

  public List<String> findWords(char[][] board, String[] words){
    List<String> result = new ArrayList<>();
    m = board.length;
    n = board[0].length;
    this.board = board;
    this.words = new HashSet<>(Arrays.asList(words));

    // 先找出所有单词的首字母，然后深度遍历的时候只遍历这些字母所在的位置
    Set<Character> allFirstChars = new HashSet<>();
    for(String word : words){
      allFirstChars.add(word.charAt(0));
    }

    // 将所有单词首字母所在的坐标保存到集合
    List<int[]> allFirstCharPoint = new ArrayList<>();
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if(allFirstChars.contains(board[i][j])){
          allFirstCharPoint.add(new int[]{i, j});
        }
      }
    }

    // 只遍历所有首字母坐标集合
    for(int[] point : allFirstCharPoint){
      visited = new boolean[m][n];
      StringBuilder sb = new StringBuilder();
      dfs212(point[0], point[1], sb, result);
    }

    return result;
  }

  private void dfs212(int i, int j, StringBuilder sb, List<String> result){
    sb.append(board[i][j]);
    visited[i][j] = true;
    String string = sb.toString();
    if(words.contains(string)){
      result.add(string);
      words.remove(string);
    }

    // 单词最长为10，达到后即不用再找
    if(sb.length() >= 10){
      return;
    }

    for(int[] dir : dirs){
      int m = i + dir[0];
      int n = j + dir[1];
      boolean isLegal = m >= 0 && m < this.m && n >= 0 && n < this.n;
      if(!isLegal || visited[m][n]){
        continue;
      }
      dfs212(m, n, sb, result);
      visited[m][n] = false;
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}