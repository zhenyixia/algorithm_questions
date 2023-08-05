package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liyapeng
 * @description 21. 合并两个有序链表 https://leetcode.cn/problems/merge-two-sorted-lists/ 11.22-12.03
 * @date 2023/8/5 11:21
 */
public class L0021MergeTwoSortedLists{
  @Test
  public void test1(){

    Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4},
        node2Arr(myMergeTwoLists(arr2Node(new int[]{1, 2, 4}), arr2Node(new int[]{1, 3, 4}))));
  }

  public ListNode arr2Node(int[] arr){
    ListNode head = new ListNode(arr[0]);
    ListNode next = head;
    for(int i = 1; i < arr.length; i++){
      next.next = new ListNode(arr[i]);
      next = next.next;
    }
    return head;
  }

  public int[] node2Arr(ListNode node){
    List<Integer> list = new ArrayList<>();
    list.add(node.val);
    while(node.next != null){
      list.add(node.next.val);
      node = node.next;
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  public ListNode myMergeTwoLists(ListNode list1, ListNode list2){
    if(list1 == null){
      return list2;
    }

    if(list2 == null){
      return list1;
    }

    ListNode head;
    ListNode list1Next = list1;
    ListNode list2Next = list2;
    if(list1.val > list2.val){
      head = list2;
      list2Next = list2.next;
    }else{
      head = list1;
      list1Next = list1.next;
    }

    ListNode next = head;
    while(list2Next != null && list1Next != null){
      if(list2Next.val <= list1Next.val){
        next.next = list2Next;
        list2Next = list2Next.next;
      }else{
        next.next = list1Next;
        list1Next = list1Next.next;
      }
      next = next.next;
    }

    if(list1Next != null){
      next.next = list1Next;
    }

    if(list2Next != null){
      next.next = list2Next;
    }

    return head;
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2){
    if(l1 == null){
      return l2;
    }else if(l2 == null){
      return l1;
    }else if(l1.val < l2.val){
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    }else{
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }
}

class ListNode{
  int val;

  ListNode next;

  ListNode(){ }

  ListNode(int val){ this.val = val; }

  ListNode(int val, ListNode next){
    this.val = val;
    this.next = next;
  }
}
