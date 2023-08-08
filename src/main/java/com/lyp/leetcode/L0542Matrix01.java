package com.lyp.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Test;

/**
 * @description 542. 01 矩阵  https://leetcode.cn/problems/01-matrix/description/
 * @author liyapeng
 * @date 2023/8/6 9:51
 */
public class L0542Matrix01{
  @Test
  public void test1(){
    Assert.assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
        updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));

    Assert.assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}},
        updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}));
  }

  public int[][] updateMatrix(int[][] mat){
    int rowNum = mat.length;
    int colNum = mat[0].length;

    // 将所有0放在队列中，并将0的标记已访问过
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rowNum][colNum];
    for(int i = 0; i < rowNum; i++){
      for(int j = 0; j < colNum; j++){
        if(mat[i][j] == 0){
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        }else{
          mat[i][j] = 0;
        }
      }
    }

    // 四个方向的操作，分别为上下左右
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while(!queue.isEmpty()){
      int[] poll = queue.poll();
      for(int[] dir : dirs){
        int newX = dir[0] + poll[0];
        int newY = dir[1] + poll[1];
        boolean legal = newX >= 0 && newX < rowNum && newY >= 0 && newY < colNum;
        if(legal && !visited[newX][newY]){
          mat[newX][newY] = mat[poll[0]][poll[1]] + 1;
          visited[newX][newY] = true;
          queue.offer(new int[]{newX, newY});
        }
      }
    }

    return mat;
  }
}
/**
 * 思路：
 */