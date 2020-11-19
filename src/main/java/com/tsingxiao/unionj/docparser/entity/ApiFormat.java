package com.tsingxiao.unionj.docparser.entity;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.docparser.entity
 * @date:2020/11/19
 */
public class ApiFormat {

  public static final String INT32 = "int32";
  public static final String INT64 = "int64";
  public static final String FLOAT = "float";
  public static final String DOUBLE = "double";

  public static final String BASE64 = "byte";
  public static final String FILE = "binary";

  // default: yyyy-MM-dd
  public static final String DATE = "date";

  // default: yyyy-MM-dd HH:mm:ss
  public static final String DATETIME = "date-time";

  public static final String PASSWORD = "password";
  public static final String EMAIL = "email";
  public static final String UUID = "uuid";
  public static final String SNOWFLAKE = "snowflake";
  public static final String ADDRESS = "address";
  public static final String NAME = "name";
  public static final String PHONE = "phone";

  // 18 - 100
  public static final String AGE = "age";
}
