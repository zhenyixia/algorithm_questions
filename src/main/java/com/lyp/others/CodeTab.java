package com.lyp.others;

import org.junit.Assert;
import org.junit.Test;

/**
 * @description questions/最小缩进操作次数-CSDN博客
 * @date 2023/11/22 21:54
 * @author liyapeng
 */
public class CodeTab{
  @Test
  public void test1(){
    Assert.assertEquals(3, calc(new int[]{1, 2, 3, 2, 1}));
    Assert.assertEquals(6, calc(new int[]{0, 1, 2, 0, 2, 4, 2, 1, 0}));
  }

  public int calc(int[] nums){
    int res = nums[0];
    for(int i = 1; i < nums.length; i++){
      if(nums[i] > nums[i - 1]){
        res += nums[i] - nums[i - 1];
      }
    }
    return res;
  }
}

/**
 * 解题参考： https://ustccoder.github.io/2021/08/10/program%20Interview11/
 * 我们发现在缩进的过程中。
 * 如果当前行的缩进大于上一行，那么在调整上一行的时候,连着本行一起调整是最优的，本次只需要调整两行之间的差值即可。
 * 如果当前行的缩进小于等于上一行，那么在调整上一行的时候，已经将本行的缩进调整为0了，则跳过本行的调整即可。
 *
 */