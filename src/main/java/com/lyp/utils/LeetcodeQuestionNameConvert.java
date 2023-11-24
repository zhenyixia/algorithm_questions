package com.lyp.utils;

import java.util.Scanner;

/**
 * @description 力扣题目名称转换
 * @date 2023/11/24 7:47
 * @author liyapeng
 */
public class LeetcodeQuestionNameConvert{

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNextLine()){
      String line = scanner.nextLine();
      test(line);
    }
  }

  public static void test(String ss){
    String[] substrings = ss.split("-");
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < substrings.length; i++){
      char[] chars = substrings[i].toCharArray();
      chars[0] = (char)(chars[0] - 32);
      sb.append(chars);
    }
    System.out.println(sb);
  }
}