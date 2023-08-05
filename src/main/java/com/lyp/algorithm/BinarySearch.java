package com.lyp.algorithm;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liyapeng
 * @description 二分法查找，用于规范二分法的标准方法
 * @date 2023/7/30 17:48
 */
public class BinarySearch{

  @Test
  public void test1(){
    Assert.assertFalse(search(new int[]{3, 1}, 2));
    Assert.assertTrue(search(new int[]{3}, 3));
    Assert.assertFalse(search(new int[]{3}, 6));
    Assert.assertTrue(search(new int[]{3, 5, 7, 9, 11}, 7));
    Assert.assertFalse(search(new int[]{3, 5, 7, 9, 11}, 6));
  }

  public boolean search(int[] arr, int target){
    Arrays.sort(arr);
    int low = 0;
    int high = arr.length - 1;
    while(low <= high){
      // 等同于 (left + right)/2 ;防止溢出,
      // 另外可以看出 ：
      // 1，当 low<high时，mid=(high - low)/2  因为是向下取整，所以肯定小于high，大于等于low
      // 2，当low与high只差1时，如果此时target大于arr[mid]时，low = mid+1 就达到等于high。
      // 此时要么target等于新的mid:arr[mid]，
      // 要么不在该数组中(因为一直以来target都在arr[high]与arr[low]之间，最后挤到一个点上，还不相等，只能是不存在)。
      // 特殊情况：如果初始 high就等于low时，即arr只有一个元素时，
      // 走完该循环 low可能大于high，该注意用于需要找最终的索引位置时
      // todo 此种情况 low可能大于原始high，high也可能小于原始low。但是mid却是不会大于原始high，也不会小于原始low
      int mid = low + ((high - low) >> 1);
      if(target < arr[mid]){
        high = mid - 1;
      }else if(target > arr[mid]){
        low = mid + 1;
      }else{
        return true;
      }
    }

    return false;
  }
}