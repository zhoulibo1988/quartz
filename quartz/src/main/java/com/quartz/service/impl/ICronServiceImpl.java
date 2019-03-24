package com.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.entity.CronEntity;
import com.quartz.mapper.CronMapper;
import com.quartz.service.ICronService;

/**
* @ClassName: ICronServiceImpl 
* @Description: 定时任务实现类
* @author Mr.zhou 
* @date Mar 22, 2019 10:12:00 PM
 */
@Service
public class ICronServiceImpl implements ICronService {
	@Autowired
	private CronMapper cronMapper;
	@Override
	public void sendEmail(String quartzName) {
		System.out.println(quartzName +" 正在发送邮件");
	}

	@Override
	public void sendSms(String quartzName) {
		System.out.println(quartzName +"正在发送短信");

	}

	@Override
	public CronEntity findByCronId(String id) {
		// TODO Auto-generated method stub
		return this.cronMapper.load(id);
	}

	@Override
	public void update(CronEntity cronEntity) {
		this.cronMapper.updateCron(cronEntity.getId(), cronEntity.getCron());
		
	}

	@Override
	public List<CronEntity> findAll() {
		// TODO Auto-generated method stub
		return this.cronMapper.queryAll();
	}

	@Override
	public void addCron(CronEntity cronEntity) {
		this.cronMapper.insert(cronEntity);
		
	}

}
