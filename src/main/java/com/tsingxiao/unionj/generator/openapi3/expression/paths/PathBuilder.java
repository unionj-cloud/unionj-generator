package com.tsingxiao.unionj.generator.openapi3.expression.paths;


import com.tsingxiao.unionj.generator.openapi3.model.paths.Operation;
import com.tsingxiao.unionj.generator.openapi3.model.paths.Path;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class PathBuilder {

  private Path path;

  public PathBuilder() {
    this.path = new Path();
  }

  public void endpoint(String endpoint) {
    this.path.setEndpoint(endpoint);
  }

  public void get(Operation get) {
    this.path.setGet(get);
  }

  public void post(Operation post) {
    this.path.setPost(post);
  }

  public void put(Operation put) {
    this.path.setPut(put);
  }

  public void delete(Operation delete) {
    this.path.setDelete(delete);
  }

  public Path build() {
    return this.path;
  }

}
