package com.skx.exercise.interception;

/**
 * Created by vj on 2015/09/24.
 */
public class InterceptorClass {
     public void before(){
          System.out.println("������InterceptorClass��������:before()!"+(Client.b++).toString());
          }
      public void after(){
          System.out.println("������InterceptorClass��������:after()!"+(Client.a++).toString());
          }
}
