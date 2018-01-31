package com.gb2260.data.pojo;

/**
 * 市、地区、自治州、盟、直辖市所辖市辖区/县汇总码、省（自治区）直辖县级行政区划汇总码
 * 01~20、51~70表示市，01、02还用于表示直辖市所辖市辖区/县汇总码
 * 21~50表示地区、自治州、盟
 * 90表示省（自治区）直辖县级行政区划汇总码
 */
public class City extends Division {
  public City(){}

  public City(String code){//直辖市区县、省（自治区）直辖县级行政区划需要生成对应汇总码
    super();
    if(code.startsWith("1101")){
      this.setCode("110100");
      this.setName("北京市");
      this.setParentCode("110000");
    }else if(code.startsWith("1102")){
      this.setCode("110200");
      this.setName("北京市郊县");
      this.setParentCode("110000");
    }else if(code.startsWith("1201")){
      this.setCode("120100");
      this.setName("天津市");
      this.setParentCode("120000");
    }else if(code.startsWith("1202")){
      this.setCode("120200");
      this.setName("天津市郊县");
      this.setParentCode("120000");
    }else if(code.startsWith("3101")){
      this.setCode("310100");
      this.setName("上海市");
      this.setParentCode("310000");
    }else if(code.startsWith("3102")){
      this.setCode("310200");
      this.setName("上海市郊县");
      this.setParentCode("310000");
    }else if(code.startsWith("5001")){
      this.setCode("500100");
      this.setName("重庆市");
      this.setParentCode("500000");
    }else if(code.startsWith("5002")){
      this.setCode("500200");
      this.setName("重庆市郊县");
      this.setParentCode("500000");
    }else if("90".equals(code.substring(2, 4))){
      this.setCode(code.substring(0, 4) + "00");
      this.setName("省/自治区直辖县级行政区划");
      this.setParentCode(code.substring(0, 2) + "0000");
    }else{
      System.err.printf("Invalid County Code %s for GB2260!\n", code);
    }
  }

  public City(String name, String code){
    super(name, code);
  }
}
