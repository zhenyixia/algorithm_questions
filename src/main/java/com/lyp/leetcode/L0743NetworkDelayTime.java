package com.lyp.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @description Dijkstra 算法+邻接矩阵 743. 网络延迟时间
 * @date 2023/11/24 19:42
 * @author liyapeng
 */
public class L0743NetworkDelayTime{
  public int networkDelayTime(int[][] times, int n, int k){
    // 定义一个邻接矩阵的初始值，除以2是为了后面累加路径时防止两个元素和溢出
    int ele = Integer.MAX_VALUE / 2;

    // 定义一个节点数组，用于存储到达指定元素的最短距离
    int[] dist = new int[n];
    Arrays.fill(dist, ele);

    // 注意节点k到k自己的距离是0，这里是一个初始化的值，后面遍历时的开始节点就是从这里开始的。
    dist[k - 1] = 0;

    // 定义邻接矩阵，并赋初值
    int[][] all = new int[n][n];
    for(int[] row : all){
      Arrays.fill(row, ele);
    }

    // 将给定的连接与权值赋给邻接矩阵，注意，这里涉及一个节点的转换，
    // 比如从 2到3距离为4，在邻接矩阵中则是 all[1][2] = 4，也就是需要减1
    for(int[] time : times){
      int row = time[0] - 1;
      int col = time[1] - 1;
      all[row][col] = time[2];
    }

    // 邻接矩阵初始化好了后，就开始遍历比较，注意遍历的次数是节点的个数。也就是每一次遍历都是对应一个节点
    // 这里需要找到下一次遍历的节点。第一次遍历是从k节点开始，然后能得出k到所有可达到的节点的距离，这里使用队列来存放每一次遍历的起始节点
    ArrayDeque<Integer> starts = new ArrayDeque<>();
    starts.offer(k - 1);

    // 代表已经将下一批循环的起点加进来了
    boolean[] hasLoop = new boolean[n];
    for(int i = 0; i < n; i++){

      // 寻找起始点，以当前所有距离与指定点最近的开始，第一次即指定点自己。使用完需要
      int start = -1;
      for(int j = 0; j < n; j++){
        if(hasLoop[j]){
          continue;
        }
        if(start == -1 || dist[j] < dist[start]){
          start = j;
        }
      }

      hasLoop[start] = true;
      for(int j = 0; j < n; j++){
        dist[j] = Math.min(dist[j], dist[start] + all[start][j]);
      }
    }

    int ans = Arrays.stream(dist).max().getAsInt();
    return ans == ele? -1: ans;
  }
}