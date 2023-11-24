package com.lyp.leetcode;

import java.util.ArrayDeque;

/**
 * @description 前缀和+单调递减栈 862. 和至少为 K 的最短子数组
 * @date 2023/11/24 7:53
 * @author liyapeng
 */
public class L0862ShortestSubarrayWithSumAtLeastK{

  public int shortestSubarray(int[] nums, int k){
    int n = nums.length, ans = n + 1;

    // 计算前缀和
    long[] s = new long[n + 1];
    for(int i = 0; i < n; ++i)
      s[i + 1] = s[i] + nums[i];

    // 存储前缀和的下标 默认存储的为： 0,1，2，...i，但是在循环的过程中会移除一部分
    ArrayDeque<Integer> indices = new ArrayDeque<>();
    for(int i = 0; i <= n; ++i){
      long curS = s[i];

      // 假设i=3，indices的第一个元素值为0时，0到3数字区间和大等于k时，循环找到最小的区间
      while(!indices.isEmpty() && curS - s[indices.peekFirst()] >= k){
        ans = Math.min(ans, i - indices.pollFirst()); // 优化一
      }

      // 注意，这里的peekLast的值仍然小于i，因为indices存储的都是i之前的数字，
      // 这一步的作用，就是减少上一步优化的循环次数
      while(!indices.isEmpty() && s[indices.peekLast()] >= curS){
        indices.pollLast(); // 优化二
      }
      indices.addLast(i);
    }
    return ans > n? -1: ans;
  }
}