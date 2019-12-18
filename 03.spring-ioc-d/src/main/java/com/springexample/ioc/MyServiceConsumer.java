package com.springexample.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("serviceConsumer") // <bean id="serviceConsumer" class="..."
public class MyServiceConsumer implements ServiceConsumer {
	
	//@Autowired // <bean id="..." class="..." autowire="byType"	
	private MessageService messageService;
	
	@Autowired // <bean id="..." class="..." autowire="byName"
	@Qualifier("xxx") // 이름이 xxx인 ServiceMessage를 의존성 주입하세요
	public void setMessageService(MessageService messageService) { //의존성 주입 통로
		this.messageService = messageService;
	}
	
	@Autowired
	@Qualifier("timeService")
	private TimeService timeService; // 주입된 의존성(객체)을 저장할 참조 변수
//	public void setTimeService(TimeService timeService) { //의존성 주입 통로
//		this.timeService = timeService;
//	}

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);		
		
		message = timeService.getTimeString();
		System.out.println(message);
	}

}








