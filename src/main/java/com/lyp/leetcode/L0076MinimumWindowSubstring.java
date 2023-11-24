package com.lyp.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 滑动窗口 76. 最小覆盖子串 、
 * https://leetcode.cn/problems/minimum-window-substring/description/?envType=list&envId=6oIkKB3y
 * @date 2023/11/24 7:07
 * @author liyapeng
 */
public class L0076MinimumWindowSubstring{

  public String minWindow(String s, String t){
    char[] parentArray = s.toCharArray();
    char[] childArray = t.toCharArray();
    Map<Character, Integer> childMap = new HashMap<>();
    for(char c : childArray){
      childMap.merge(c, 1, Integer::sum);
    }

    Map<Character, Integer> parentMap = new HashMap<>();
    int left = 0;
    int minLeft = 0;
    int minRight = -1;
    for(int right = 0; right < parentArray.length; right++){
      parentMap.merge(parentArray[right], 1, Integer::sum);
      while(mapContains(parentMap, childMap)){
        if(minRight == -1){
          minRight = right;
        }
        if(valSize(parentMap) < (minRight - minLeft + 1)){
          minLeft = left;
          minRight = right;
        }
        subMapVal(parentMap, parentArray[left]);
        left++;
      }
    }

    return minRight == -1? "": s.substring(minLeft, minRight + 1);
  }

  private void subMapVal(Map<Character, Integer> parentMap, char c){
    Integer integer = parentMap.get(c);
    if(integer == 1){
      parentMap.remove(c);
    }else{
      parentMap.put(c, integer - 1);
    }
  }

  private int valSize(Map<Character, Integer> parentMap){
    int sum = 0;
    for(Integer value : parentMap.values()){
      sum += value;
    }
    return sum;
  }

  private boolean mapContains(Map<Character, Integer> parentMap, Map<Character, Integer> childMap){
    for(Character character : childMap.keySet()){
      if(parentMap.get(character) == null || childMap.get(character) > parentMap.get(character)){
        return false;
      }
    }
    return true;
  }
}