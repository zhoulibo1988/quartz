package com.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
* @ClassName: QuartzApplication 
* @Description: 定时任务调度中心
* @author Mr.zhou 
* @date Mar 24, 2019 5:08:17 PM
 */
@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

}
