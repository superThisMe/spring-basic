package com.springexample.ioc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeService implements TimeService {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	public String getTimeString() {

		return format.format(new Date());

	}

}
