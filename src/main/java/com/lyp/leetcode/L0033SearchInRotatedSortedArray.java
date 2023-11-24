package com.lyp.leetcode;

/**
 * @description 二分查找  33. 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?envType=list&envId
 * =28zw8AwM
 * @date 2023/11/23 7:29
 * @author liyapeng
 */
public class L0033SearchInRotatedSortedArray{
  public int search(int[] nums, int target){
    int len = nums.length;
    int low = 0;
    int high = len - 1;

    // 二分法找最小元素的坐标；1,n; 2,n-1对比。
    int maxIndex = 0;
    while(nums[low] > nums[high]){
      if(low < len - 1){
        maxIndex = low;
        low += 1;
      }
      // 增加条件：nums[low] > nums[high] ，保证 maxIndex一直处于左侧大于右侧
      if(nums[low] > nums[high] && high > 0){
        maxIndex = high;
        high -= 1;
      }
    }

    // 最后的maxIndex坐标的值，要么是最大值，要么是最小值，而我们需要最大值做闭区间二分查找
    if(maxIndex - 1 >= 0 && nums[maxIndex - 1] > nums[maxIndex]){
      maxIndex = maxIndex - 1;
    }

    // 分别对 0-low low-(len-1) 二分查找
    int hasFind = binaryFind(nums, 0, maxIndex, target);
    if(hasFind == -1){
      hasFind = binaryFind(nums, maxIndex + 1, len - 1, target);
    }
    return hasFind;
  }

  private int binaryFind(int[] nums, int low, int high, int target){
    while(low <= high){
      int mid = low + ((high - low) >> 1);
      if(target > nums[mid]){
        low = mid + 1;
      }else if(target < nums[mid]){
        high = mid - 1;
      }else{
        return mid;
      }
    }

    return -1;
  }
}