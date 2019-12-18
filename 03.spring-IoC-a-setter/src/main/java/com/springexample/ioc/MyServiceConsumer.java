package com.springexample.ioc;

public class MyServiceConsumer implements ServiceConsumer {

	private MessageService messageService;
	private TimeService timeService;
	private String test = "-a";

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}
	
	public MyServiceConsumer(MessageService messageService) {
		this.messageService = messageService;
	}

	public void doSomething() {
		// DI (setter injection)
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message+test);
	}

}

 