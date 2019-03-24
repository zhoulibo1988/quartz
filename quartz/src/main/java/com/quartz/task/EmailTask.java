package com.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.service.ICronService;
/**
* @ClassName: EmailTask 
* @Description: 发送邮件任务执行类
* @author Mr.zhou 
* @date Mar 22, 2019 10:40:13 PM
 */
public class EmailTask implements Job{
	private Logger logger = LoggerFactory.getLogger(EmailTask.class);
	@Autowired
    private ICronService iCronService;
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("EmailTask task start execute.");
		//模拟任务执行
        iCronService.sendEmail(jobExecutionContext.getJobDetail().getKey().getName());
	}

}
