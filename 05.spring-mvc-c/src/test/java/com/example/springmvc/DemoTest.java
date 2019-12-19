package com.example.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springmvc.config.WebConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:path-for-bean-config.xml" }) // xml을 사용할 때
@ContextConfiguration(classes = { WebConfig.class }) // java class를 사용할 때
@Log4j // lombok이 Log4j 객체를 자동으로 만들어주는 annotation (변수 이름 : log)
public class DemoTest {

	@Test
	public void testOne() {
		
//		int x = 10 / 0;
//		System.out.println("Test Method : " + x);
		
		log.info("---------------------------");
		log.error("Log Message in Test Method");
		
	}
	
}
