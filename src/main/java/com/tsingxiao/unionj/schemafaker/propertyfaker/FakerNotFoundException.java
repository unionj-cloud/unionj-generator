package com.tsingxiao.unionj.schemafaker.propertyfaker;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.schemafaker
 * @date:2020/11/20
 */
public class FakerNotFoundException extends Exception {
  /**
   * Constructs a new runtime exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   */
  public FakerNotFoundException(String message) {
    super(message);
  }
}
