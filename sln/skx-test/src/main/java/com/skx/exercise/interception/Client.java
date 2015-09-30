package com.skx.exercise.interception;

import com.skx.exercise.interception.proxy.DynamicProxyHandler;

/**
 * Created by vj on 2015/09/24.
 */
public class Client {
    public static Integer b=0;
    public static Integer a=0;
    public static void main(String args[]) {
        DynamicProxyHandler handler = new DynamicProxyHandler();
        BusinessInterface business = new BusinessClass();
        BusinessInterface aa = (BusinessInterface)handler.bind(business);
aa.doSomething();
        Object bb=aa;
        Object cc=bb;
        Object dd=aa;
        String aaa="";
        String bbb="";
        //businessProxy.doSomething();
       //System.out.println(business.getString(1));
    }
}
