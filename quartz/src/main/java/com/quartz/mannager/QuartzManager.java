package com.quartz.mannager;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.quartz.entity.CronEntity;
import com.quartz.service.ICronService;

/**
 * 
 * @ClassName: QuartzManager
 * @Description: 定时器操作类
 * @author Mr.zhou
 * @date Mar 22, 2019 10:26:40 PM
 */
@Configuration
public class QuartzManager {
	private static final String group="group1";
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private ICronService iCronService;
	/**
	* @Title: start 
	* @Description: 开启定时任务
	* @param @param id
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void start(String id) throws SchedulerException {
		CronEntity cronEntity = iCronService.findByCronId(id);
		if (cronEntity != null) {
			startJob(scheduler, cronEntity.getQuarzName(), cronEntity.getCron(), cronEntity.getSchedulerClass());
		}
	}

	private void startJob(Scheduler scheduler, String name, String cron, String className) throws SchedulerException {
		// 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
		// JobDetail 是具体Job实例
		Class<Job> jobClass = null;
		try {
			// 实例化具体的Job任务
			jobClass = (Class<Job>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
		// 基于表达式构建触发器
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		// CronTrigger表达式触发器 继承于Trigger
		// TriggerBuilder 用于构建触发器实例
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name, group)
				.withSchedule(cronScheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	/**
	 * 
	* @Title: getJobInfo 
	* @Description: 获取一个jop信息 
	* @param @param name
	* @param @param group
	* @param @return
	* @param @throws SchedulerException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getJobInfo(String name, String group) throws SchedulerException {
		TriggerKey triggerKey = new TriggerKey(name, group);
		CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
				scheduler.getTriggerState(triggerKey).name());
	}
	/**
	* @Title: modifyJob 
	* @Description: 修改某个任务的执行时间
	* @param @param name
	* @param @param group
	* @param @param time
	* @param @return
	* @param @throws SchedulerException    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean modifyJob(String name, String group, String time) throws SchedulerException {
		Date date = null;
		TriggerKey triggerKey = new TriggerKey(name, group);
		CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		String oldTime = cronTrigger.getCronExpression();
		if (!oldTime.equalsIgnoreCase(time)) {
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
					.withSchedule(cronScheduleBuilder).build();
			date = scheduler.rescheduleJob(triggerKey, trigger);
		}
		return date != null;
	}
	/**
	 * 
	* @Title: pauseAllJob 
	* @Description: 暂停所有定时任务
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }
	/**
	* @Title: pauseJob 
	* @Description: 暂停某个定时任务
	* @param @param name
	* @param @param group
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }
	/**
	* @Title: resumeAllJob 
	* @Description: 恢复所有定时任务
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }
	/**
	 * 
	* @Title: resumeJob 
	* @Description: 恢复某个定时任务
	* @param @param name
	* @param @param group
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }
	/**
	* @Title: deleteJob 
	* @Description: 删除某个定时任务
	* @param @param name
	* @param @param group
	* @param @throws SchedulerException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }
}
