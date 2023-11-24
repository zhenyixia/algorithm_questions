package com.lyp.leetcode;

import java.util.ArrayDeque;

/**
 * @description  单调递减栈 42. 接雨水
 * https://leetcode.cn/problems/trapping-rain-water/description/?envType=list&envId=dDSiHKuw
 * @date 2023/11/24 7:02
 * @author liyapeng
 */
public class L0042TrappingRainWater{
  public int trap(int[] height){
    ArrayDeque<Integer> deque = new ArrayDeque<>();
    int sum = 0;
    for(int i = 0; i < height.length; i++){
      // 栈中的元素如果小于当前则有可能有面积，如果大于当前则不可能
      while(!deque.isEmpty() && height[i] > height[deque.peekLast()]){
        // 面积=底*高，而底即为当前向左第一个的高度
        int d = deque.pollLast();

        // 向前平推，和现在底一样的直接弹出
        while(!deque.isEmpty() && height[d] == height[deque.peekLast()]){
          deque.pollLast();
        }

        // 遇到把边的就计算面积
        if(!deque.isEmpty() && height[d] < height[deque.peekLast()]){
          sum += (i - deque.peekLast() - 1) * (Math.min(height[deque.peekLast()], height[i]) - height[d]);
        }
      }

      deque.addLast(i);
    }
    return sum;
  }
}