package com.gb2260.data.util;

import com.gb2260.data.common.Revision;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class IDCardDivisionCode {
  public Map<String, String> parseDivision(Revision revision){
    Map<String, String> ret = new TreeMap<>();
    InputStream inputStream = getClass().getResourceAsStream("/data/" + revision.getCode() + ".tsv");
    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
    try {
      while (r.ready()) {
        String line = r.readLine();
        String[] split = line.split("\t");
        String code = split[2];
        String name = split[3];
        if (Pattern.matches("^\\d{6}$", code)){
          ret.put(code, StringUtils.trimToNull(name));
        }else {//非法编码
          System.err.printf("Invalid Code %s[%s] GB2260 data!\n", revision.getCode(), code);
        }
      }
    }catch (IOException e) {
      System.err.println("Error in loading GB2260 data!");
      throw new RuntimeException(e);
    }
    return ret;
  }
}
