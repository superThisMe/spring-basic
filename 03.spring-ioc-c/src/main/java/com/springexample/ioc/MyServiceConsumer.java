package com.springexample.ioc;

public class MyServiceConsumer implements ServiceConsumer {
	
	private MessageService messageService;
	public void setMessageService(MessageService messageService) { //의존성 주입 통로
		this.messageService = messageService;
	}
	
	private TimeService timeService; // 주입된 의존성(객체)을 저장할 참조 변수
	public void setTimeService(TimeService timeService) { //의존성 주입 통로
		this.timeService = timeService;
	}

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		
		// 현재 시간을 "2019년 12월 13일 10시 40분 16초" 형식으로 출력
		// 직접 구현하지 않고 다른 클래스의 기능을 호출해서 사용
		
		//1. 클래스 직접 사용 
		//MyTimeService timeService = new MyTimeService();
		
		//2. 인터페이스 - 클래스 사용
		//TimeService timeService = new MyTimeService();
		
		//3. 인터페이스 - 직접 구현한 팩토리 사용
//		MyBeanFactory beanFactory = new MyBeanFactory();
//		TimeService timeService = beanFactory.getBean("timeService");
		
		//4. 인터페이스 - 스프링 IoC 컨테이너 사용
//		GenericXmlApplicationContext context = 
//				new GenericXmlApplicationContext("app-context.xml");
//		
//		TimeService timeService = (TimeService)context.getBean("timeService");
		
		//5. 인터페이스 - 스프링 의존성 주입 사용
		//   작성할 코드는 없고 멤버변수 + setter 메서드 + bean 설정 파일 사용
		
		message = timeService.getTimeString();
		System.out.println(message);
	}
	
	public void init() {
		System.out.println("init method");
	}
	
	public void destroy() {
		System.out.println("destroy method");
	}

}








