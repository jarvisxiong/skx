package com.skx.exercise.interception;

/**
 * Created by vj on 2015/09/24.
 */
public class BusinessClass implements BusinessInterface{
    public void doSomething() {
        System.out.println("ҵ�����BusinessClass��������:doSomething()");
    }

//    public String getString(Integer a){
//        return ((Integer)(a+1)).toString();
//    }
}
