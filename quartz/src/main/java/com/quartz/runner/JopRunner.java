package com.quartz.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.quartz.entity.CronEntity;
import com.quartz.mannager.QuartzManager;
import com.quartz.service.ICronService;
@Component
@Order(value = 1)   //执行顺序控制
public class JopRunner implements ApplicationRunner{
	@Autowired
	private ICronService  iCronService;
	@Autowired
	QuartzManager quartzManager;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("------->开始加载数据库任务");
		List<CronEntity> list=iCronService.findAll();
		System.out.println("------->获取加载数据库任务个数为"+list.size()+"个");
		for (CronEntity cronEntity : list) {
			quartzManager.start(cronEntity.getId());
		}
		System.out.println("------->任务调度加载完成");
	}

}
