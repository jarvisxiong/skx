package com.skx.exercise.interception;

/**
 * Created by vj on 2015/09/24.
 */
public class InterceptorClass {
     public void before(){
          System.out.println("拦截器InterceptorClass方法调用:before()!"+(Client.b++).toString());
          }
      public void after(){
          System.out.println("拦截器InterceptorClass方法调用:after()!"+(Client.a++).toString());
          }
}
