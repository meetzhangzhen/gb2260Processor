package com.gb2260.data.pojo;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@EqualsAndHashCode(of = {"code"})
public class Division {
  private String name;
  private String code;
  private String parentCode;
  private String revision;
  private Set<Division> prefectures;

  public Division(){
    this.prefectures = new LinkedHashSet<>();
  }

  public Division(String name, String code){
    this();
    this.name = name;
    this.code = code;
  }

  public void addPrefecture(Division prefecture){
    prefectures.add(prefecture);
  }

  public void travelPrint(){
    System.out.println(code + '\t' + name);
    if(!CollectionUtils.isEmpty(prefectures)){
      prefectures.stream().forEach(prefecture -> {
        prefecture.travelPrint();
      });
    }
  }
}
