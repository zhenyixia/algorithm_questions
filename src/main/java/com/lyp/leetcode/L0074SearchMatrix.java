package com.lyp.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liyapeng
 * @description 74. 搜索二维矩阵 https://leetcode.cn/problems/search-a-2d-matrix/ 16.56--
 * @date 2023/7/30 16:55
 */
public class L0074SearchMatrix{

  public boolean searchMatrix(int[][] matrix, int target){
    for(int[] ints : matrix){
      for(int anInt : ints){
        if(anInt == target){
          return true;
        }
      }
    }

    return false;
  }

  @Test
  public void test2(){
    Assert.assertTrue(searchMatrix2(new int[][]{{1, 3}}, 3));
    Assert.assertFalse(searchMatrix2(new int[][]{{1, 1}}, 3));
    Assert.assertFalse(searchMatrix2(new int[][]{{1}}, 3));
    Assert.assertTrue(searchMatrix2(new int[][]{{3}}, 3));
    Assert.assertTrue(searchMatrix2(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
    Assert.assertFalse(searchMatrix2(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
  }

  public boolean searchMatrix2(int[][] matrix, int target){
    int row = matrix.length;
    int col = matrix[0].length;
    // 第一处错误，缺少边界值判断
    if(row == col && row == 1){
      return matrix[0][0] == target;
    }

    // 对第一列进行二分法搜索
    int i = 0, j = row - 1;
    while(i <= j){
      int index = (i + j) / 2;
      if(matrix[index][0] < target){
        i = index + 1;
      }else if(matrix[index][0] > target){
        j = index - 1;
      }else{
        // 找到即终止
        return true;
      }
    }

    // 确定行索引 第二处错误，如果只有一行时i=0，但是可能走else，此时i-1=-1，会让后面数组出问题
    /*int targetRow;
    if(matrix[i][0] < target){
      targetRow = i;
    }else{
      targetRow = i - 1;
    }*/

    int targetRow = i;
    if(matrix[i][0] > target && i > 0){
      targetRow = i - 1;
    }

    // 对行进行二分查找
    i = 0;
    j = col - 1;
    while(i <= j){
      int index = (i + j) / 2;
      if(matrix[targetRow][index] < target){
        i = index + 1;
      }else if(matrix[targetRow][index] > target){
        j = index - 1;
      }else{
        // 找到即终止
        return true;
      }
    }

    return false;
  }

  @Test
  public void test3(){
    Assert.assertTrue(searchMatrix3(new int[][]{{1, 3}}, 3));
    Assert.assertFalse(searchMatrix3(new int[][]{{1, 1}}, 3));
    Assert.assertFalse(searchMatrix3(new int[][]{{1}}, 3));
    Assert.assertTrue(searchMatrix3(new int[][]{{3}}, 3));
    Assert.assertTrue(searchMatrix3(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
    Assert.assertFalse(searchMatrix3(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
  }

  public boolean searchMatrix3(int[][] matrix, int target){
    int row = matrix.length;
    int col = matrix[0].length;

    // 对第一列进行二分查找
    int targetRow = 0;
    int low = 0, high = row - 1;
    while(low <= high){
      int mid = low + ((high - low) >> 1);
      targetRow = mid; // mid必然在 0与row-1之间
      if(target < matrix[mid][0]){
        high = mid - 1;
      }else if(target > matrix[mid][0]){
        low = mid +1;
      }else{
        return true;
      }
    }

    // 确定目标行，此时行首要么小于target，此时不用处理，如果大于target则要到上一行。但要注意只有一行的情况，所以要加判断
    if(matrix[targetRow][0] > target && targetRow != 0){
      targetRow -= 1;
    }

    // 对目标行进行二分查找
    low = 0;
    high = col - 1;
    while(low <= high){
      int mid = low + ((high - low) >> 1);
      if(target < matrix[targetRow][mid]){
        high = mid - 1;
      }else if(target > matrix[targetRow][mid]){
        low = mid + 1;
      }else{
        return true;
      }
    }

    return false;
  }
}

/**
 * 思路1，先按暴力破解 m n 在1到100间，最大元素个数 为 10000个。而target 在 -10000和 10000间，不存在超时问题
 * 思路2： 二分法，先对第一列进行二分法，找不到再对最后的范围进行二分法
 */