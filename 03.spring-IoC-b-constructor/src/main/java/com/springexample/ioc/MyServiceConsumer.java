package com.springexample.ioc;

public class MyServiceConsumer implements ServiceConsumer {

	private MessageService messageService;
	private TimeService timeService;
	private String test = "-b";

	public MyServiceConsumer() {}
	
	public MyServiceConsumer(MessageService messageService, TimeService timeService) {
		this.messageService = messageService;
		this.timeService = timeService;
	}

	public void doSomething() {
		// DI (constructor injection)
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message+test);
	}

}

 