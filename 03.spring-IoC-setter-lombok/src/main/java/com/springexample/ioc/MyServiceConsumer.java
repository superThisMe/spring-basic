package com.springexample.ioc;

import lombok.Setter;

public class MyServiceConsumer implements ServiceConsumer {

	@Setter
	private MessageService messageService;
	@Setter
	private TimeService timeService;
	private String test = "-a-lombok";
	
	public MyServiceConsumer(MessageService messageService) {
		this.messageService = messageService;
	}

	public void doSomething() {
		// DI (setter injection use lombok)
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message+test); 
	}

}

 