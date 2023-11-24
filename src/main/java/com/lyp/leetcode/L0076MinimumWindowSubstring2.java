package com.lyp.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 滑动窗口  76. 最小覆盖子串
 * https://leetcode.cn/problems/minimum-window-substring/description/?envType=list&envId=6oIkKB3y
 * @date 2023/11/24 7:07
 * @author liyapeng
 */
public class L0076MinimumWindowSubstring2{

  // todo 待加注解
  public String minWindow(String s, String t) {
    char[] parentArray = s.toCharArray();
    char[] childArray = t.toCharArray();
    Map<Character, Integer> childMap = new HashMap<>();
    for (char c : childArray) {
      childMap.merge(c, 1, Integer::sum);
    }
    int minLeft = 0;
    int minRight = -1;
    for (int right = 0, left = 0; right < parentArray.length; right++) {
      if (childMap.containsKey(parentArray[right])) {
        childMap.put(parentArray[right], childMap.get(parentArray[right]) - 1);
      }
      while (Collections.max(childMap.values()) == 0) {
        if (minRight == -1) {
          minRight = right;
        }

        if (right - left < minRight - minLeft) {
          minRight = right;
          minLeft = left;
        }

        if (childMap.containsKey(parentArray[left])) {
          childMap.put(parentArray[left], childMap.get(parentArray[left]) + 1);
        }

        left++;
      }
    }

    return minRight == -1 ? "" : s.substring(minLeft, minRight + 1);
  }
}