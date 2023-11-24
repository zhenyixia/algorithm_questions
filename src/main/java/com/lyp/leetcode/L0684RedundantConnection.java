package com.lyp.leetcode;

/**
 * @description 并查集 684. 冗余连接
 * https://leetcode.cn/problems/redundant-connection/description/?envType=list&envId=G2UwSxfX
 * @date 2023/11/24 6:48
 * @author liyapeng
 */
public class L0684RedundantConnection{
  public int[] findRedundantConnection(int[][] edges){
    int len = edges.length;

    //初始化代表节点，每个节点的代表节点是自己。这里的parent的index为自己，值为代表节点值
    int[] parent = new int[len + 1];
    for(int i = 0; i <= len; i++){
      parent[i] = i;
    }

    for(int[] edge : edges){
      // 找到两个节点所在集合的代表节点
      int set1 = find(edge[0], parent);
      int set2 = find(edge[1], parent);

      //两个代表节点相同，说明出现闭环，返回答案
      if(set1 == set2){
        return edge;
      }else{
        // 两个集合独立，则合并为集合。将前一个集合的代表节点合并到后一个集合代表节点上
        parent[set1] = set2;
      }
    }

    return new int[2];
  }

  // 查找路径并返回代表节点，实际上就是给定当前节点，返回该节点所在集合的代表节点
  private int find(int node, int[] parent){
    while(node != parent[node]){
      node = parent[node];
    }

    return node;
  }
}