package com.springexample.ioc;

public class MyBeanFactory {
	
	public TimeService getBean(String beanName) {
		TimeService timeService = null;
		switch (beanName) {
		case "timeService": timeService = new MyTimeService();
		}
		return timeService;
	}

}
