package com.springexample.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//bean 설정 파일(app-context.xml)과 같은 기능 수행 클래스 등록
public class MyBeanConfig {
	
	@Bean // <bean id="" class="" or @Component
	public ServiceConsumer serviceConsumer() {
		
		MyServiceConsumer sc = new MyServiceConsumer();
		sc.setMessageService(messageService()); //의존성 주입
		sc.setTimeService(timeService()); //의존성 주입
		return sc;
		
	}
	
	@Bean 
	public MessageService messageService() {
		return new MyMessageService();
	}
	
	@Bean
	public TimeService timeService() {
		return new MyTimeService();
	}

}












