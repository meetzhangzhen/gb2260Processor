package com.gb2260.data.common;

public enum Revision {
  V2017("201712"),
  V2016("201612"),
  V2015("201512"),
  V2014("201412"),
  V2013("201312"),
  V2012("201212"),
  V2011("201112"),
  V2010("201012"),
  V2009("200912"),
  V2008("200812"),
  V2007("200712"),
  V2006("200612"),
  V2005("200512"),
  V2004("200412"),
  V2003("200312"),
  V2002("200212"),
  V2001("200112"),
  V2000("200012"),
  V1999("199912"),
  V1998("199812"),
  V1997("199712"),
  V1996("199612"),
  V1995("199512"),
  V1994("199412"),
  V1993("199312"),
  V1992("199212"),
  V1991("199112"),
  V1990("199012"),
  V1989("198912"),
  V1988("198812"),
  V1987("198712"),
  V1986("198612"),
  V1985("198512"),
  V1984("198412"),
  V1983("198312"),
  V1982("198212"),
  V1981("198112"),
  V1980("198012"),
  ;

  private final String code;

  Revision(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}