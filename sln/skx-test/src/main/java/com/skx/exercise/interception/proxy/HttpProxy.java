package com.skx.exercise.interception.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.Naming;

public class HttpProxy implements InvocationHandler {

	private Class<?> type;

	public HttpProxy(Class<?> type){
		this.type = type;

		System.out.println("HttpProxy constructor");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("HttpProxy invoke");

//		Message message = new Message();
//		{
//			message.setType(this.type.getName());
//			message.setMethod(method.getName());
//			message.setPars(args);
//		}
//
//		IRmiService rmiService = (IRmiService) Naming.lookup("rmi://localhost:8888/RHello/t");
//
//		message = rmiService.invoke( message );
//
//		return message.getReturnObject();
		return "";
	}
}