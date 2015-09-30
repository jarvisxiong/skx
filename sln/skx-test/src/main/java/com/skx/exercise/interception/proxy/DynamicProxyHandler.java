package com.skx.exercise.interception.proxy;

import com.skx.exercise.interception.InterceptorClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by vj on 2015/09/24.
 */
public class DynamicProxyHandler  implements InvocationHandler {
    private Object business; //���������

    private InterceptorClass interceptor = new InterceptorClass(); //������

    /**
     * ��̬����һ�����������,���󶨱�������ʹ�������
     *
     * @param business
     * @return ���������
     */
    public Object bind(Object business) {
        this.business = business;
        return Proxy.newProxyInstance(
                //���������ClassLoader

                business.getClass().getClassLoader(),
                //Ҫ������Ľӿ�,���������ض�����Զ�����ʵ������Щ�ӿ�

                business.getClass().getInterfaces(),
                //������������

                 this);
    }

    /**
     * ����Ҫ���õķ���,���ڷ�������ǰ������������ķ���.
     *
     * @param proxy  ���������
     * @param method ������Ľӿڷ���
     * @param args   ������ӿڷ����Ĳ���
     * @return �������÷��صĽ��
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method==null)return null;
        Object result = null;
        interceptor.before();
        result = method.invoke(business, args);
        interceptor.after();
        return result; //To change body of implemented methods use File | Settings | File Templates.

    }
}
