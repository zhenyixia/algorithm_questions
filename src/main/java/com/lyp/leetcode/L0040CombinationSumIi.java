package com.lyp.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @description 回溯+大剪枝+小剪枝  40. 组合总和 II
 * @date 2023/11/24 19:46
 * @author liyapeng
 */
public class L0040CombinationSumIi{
  public List<List<Integer>> combinationSum2(int[] candidates, int target){
    Arrays.sort(candidates);

    List<List<Integer>> result = new ArrayList<>();

    Deque<Integer> deque = new ArrayDeque<>();
    for(int i = 0; i < candidates.length; i++){
      if(i != 0 && candidates[i] == candidates[i - 1]){
        continue;
      }
      deque.push(candidates[i]);
      dfs40(candidates, i + 1, target - candidates[i], result, deque);
      deque.poll();
    }

    return result;
  }

  private void dfs40(int[] candidates, int i, int target, List<List<Integer>> result, Deque<Integer> deque){
    if(target == 0){
      List<Integer> list = new ArrayList<>(deque);
      result.add(list);
      return;
    }

    for(int j = i; j < candidates.length; j++){
      if(target < candidates[j]){
        return;
      }
      if(j != i && candidates[j] == candidates[j - 1]){
        continue;
      }

      deque.push(candidates[j]);
      dfs40(candidates, j + 1, target - candidates[j], result, deque);
      deque.poll();
    }
  }
}