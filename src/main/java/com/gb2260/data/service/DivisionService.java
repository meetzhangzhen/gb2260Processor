package com.gb2260.data.service;

import com.gb2260.data.common.Revision;
import com.gb2260.data.util.GB2260;
import com.gb2260.data.util.IDCardDivisionCode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DivisionService {

  public static void main(String[] args) {
    printAllCode();
  }

  /**
   * 输出最新版的码表
   */
  public static void pringNewCode(){
    GB2260 gb2260 = new GB2260();
    gb2260.printNormal();
  }

  /**
   * 输出历史所有的区划代码对应区划名
   */
  public static void printAllCode(){
    Map<String, String> data = new TreeMap<>();
    List<Revision> revisionList = Arrays.asList(Revision.values());
    Collections.reverse(revisionList);
    revisionList.stream().forEach(revision -> {
      data.putAll(new IDCardDivisionCode().parseDivision(revision));
    });
    data.keySet().stream().forEach(key -> {
      System.out.println(key + '\t' + data.get(key));
    });
  }
}
