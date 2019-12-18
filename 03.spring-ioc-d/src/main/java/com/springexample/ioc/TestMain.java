package com.springexample.ioc;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		
	GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("app-context.xml");

		ServiceConsumer serviceConsumer = 
				(ServiceConsumer)ctx.getBean("serviceConsumer");

		serviceConsumer.doSomething();
		
		ctx.close();

	}

}








