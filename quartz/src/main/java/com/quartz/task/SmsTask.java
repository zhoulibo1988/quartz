package com.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.service.ICronService;
/**
* @ClassName: SmsTask 
* @Description: 发送短信任务执行类
* @author Mr.zhou 
* @date Mar 22, 2019 10:43:46 PM
 */
public class SmsTask implements Job{

	private Logger logger = LoggerFactory.getLogger(SmsTask.class);
	 
    @Autowired
    private ICronService iCronService;
 
 
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("SmsTask task start execute.");
        //模拟任务执行
        iCronService.sendSms(jobExecutionContext.getJobDetail().getKey().getName());
    }

}
