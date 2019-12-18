package com.springexample.ioc;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyServiceConsumer implements ServiceConsumer {

	@NonNull
	private MessageService messageService;
	@NonNull
	private TimeService timeService;
	private String test = "-b2-lombok";

	public void doSomething() {
		// DI (constructor injection using lombok)
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message + test);
	}

}