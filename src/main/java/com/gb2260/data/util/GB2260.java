package com.gb2260.data.util;

import com.gb2260.data.common.Revision;
import com.gb2260.data.pojo.City;
import com.gb2260.data.pojo.County;
import com.gb2260.data.pojo.Division;
import com.gb2260.data.pojo.Province;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class GB2260 {
  private final Revision revision;
  private Map<String, Division> data;
  private ArrayList<Province> provinces;

  public GB2260() {
    this(Revision.V2017);
  }

  public GB2260(Revision revision) {
    this.revision = revision;
    data = new LinkedHashMap<>();
    provinces = new ArrayList<>();
    InputStream inputStream = getClass().getResourceAsStream("/data/" + revision.getCode() + ".tsv");
    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
    try {
      while (r.ready()) {
        String line = r.readLine();
        String[] split = line.split("\t");
        String code = split[2];
        String name = split[3];
        if (Pattern.matches("^\\d{2}0{4}$", code)) {//省XX0000
          Province province = new Province(name, code);
          province.setRevision(revision.getCode());
          data.put(code, province);
          provinces.add(province);
        } else if (Pattern.matches("^\\d{4}0{2}$", code)){//市XXXX00
          String provinceCode = code.substring(0, 2) + "0000";
          if(!data.containsKey(provinceCode)){//所属省编码应先于市编码加入
            System.err.printf("Invalid City Code %s GB2260 data!\n", code);
            continue;
          }
          Province province = (Province) data.get(provinceCode);
          City city = new City(name, code);
          city.setParentCode(provinceCode);
          city.setRevision(revision.getCode());
          data.put(code, city);
          province.addPrefecture(city);
        } else if (Pattern.matches("^\\d{6}$", code)){//县XXXXXX
          String provinceCode = code.substring(0, 2) + "0000";
          if(!data.containsKey(provinceCode)){//所属省编码应先于市编码加入
            System.err.printf("Invalid County Code %s GB2260 data!\n", code);
            continue;
          }
          Province province = (Province) data.get(provinceCode);
          String cityCode = code.substring(0, 4) + "00";
          if(!data.containsKey(cityCode)){//直辖市的区县或者省直辖县和县级市
            City city = new City(code);
            if(StringUtils.isBlank(city.getCode())){
              continue;
            }
            city.setRevision(revision.getCode());
            province.addPrefecture(city);
            data.put(city.getCode(), city);
          }
          City city = (City) data.get(cityCode);
          County county = new County(name, code);
          county.setParentCode(cityCode);
          county.setRevision(revision.getCode());
          city.addPrefecture(county);
        } else {//非法编码
          System.err.printf("Invalid Code %s[%s] GB2260 data!\n", revision.getCode(), code);
        }
      }
    } catch (IOException e) {
      System.err.println("Error in loading GB2260 data!");
      throw new RuntimeException(e);
    }
  }

  public void printNormal(){
    provinces.stream().forEach(province -> {
      province.travelPrint();
    });
  }
}
