package com.lyp.leetcode;

/**
 * @description 滑动窗口 209 长度最小的子数组
 * @date 2023/11/24 7:15
 * @author liyapeng
 */
public class L0209MinimumSizeSubarraySum{
  public int minSubArrayLen(int target, int[] nums){
    int left = 0, right = 0, sum = 0, res = Integer.MAX_VALUE;
    while(right < nums.length){
      sum += nums[right];
      while(sum >= target){
        res = Math.min(res, right - left + 1);
        sum -= nums[left];
        left++;
      }
      right++;
    }
    return res == Integer.MAX_VALUE? 0: res;
  }
}