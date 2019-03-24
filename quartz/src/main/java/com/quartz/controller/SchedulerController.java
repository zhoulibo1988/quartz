package com.quartz.controller;

import java.util.Date;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.entity.CronEntity;
import com.quartz.mannager.QuartzManager;
import com.quartz.service.ICronService;

/**
 * 
* @ClassName: SchedulerController 
* @Description: 入口
* @author Mr.zhou 
* @date Mar 22, 2019 10:46:18 PM
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
	@Autowired
	QuartzManager quartzManager;
	@Autowired
	ICronService iCronService;
	/**
	* @Title: start 
	* @Description: 启动某一个定时任务
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	@ResponseBody
	public String start(@RequestParam(name = "id", defaultValue = "") String id) {
		try {
			quartzManager.start(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return "启动成功";
	}
	/**
	 * 
	* @Title: addQuartz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/addQuartz")
	@ResponseBody
	public String addQuartz() {
		CronEntity cronEntity=new CronEntity();
		cronEntity.setId("2");
		cronEntity.setUserId("BBB");
		cronEntity.setCron("0/10 * * * * ?");
		cronEntity.setQuarzName("用户BBB");
		cronEntity.setSchedulerClass("com.quartz.task.SmsTask");
		cronEntity.setTime(new Date());
		iCronService.addCron(cronEntity);
		return "success";
	}
	/**
	* @Title: getJopinfo 
	* @Description: 获取一个任务的详情
	* @param @param name
	* @param @param group
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/getJopinfo")
	@ResponseBody
	public String getJopinfo(@RequestParam String name,String group) {
		String jopinfo=null;
		try {
			jopinfo = quartzManager.getJobInfo(name, group);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jopinfo;
	}
	/**
	* @Title: upddateJop 
	* @Description: 修改一个定时任务的执行时间
	* @param @param name
	* @param @param group
	* @param @param time
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/updateJop")
	@ResponseBody
	public String upddateJop(@RequestParam String name,String group,String time,String id) {
		try {
			boolean updateinfo=quartzManager.modifyJob(name, group, time);
			if(updateinfo) {
				//update mysql
				CronEntity cronEntity=new CronEntity();
				cronEntity.setId(id);
				cronEntity.setCron(time);
				iCronService.update(cronEntity);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	/**
	* @Title: pauseJob 
	* @Description: 暂停某一个定时任务
	* @param @param name
	* @param @param group
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/pauseJob")
	@ResponseBody
	public String pauseJob(@RequestParam String name,String group) {
		try {
			quartzManager.pauseJob(name, group);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	* @Title: resumeJob 
	* @Description: 恢复某一个定时器
	* @param @param name
	* @param @param group
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/resumeJob")
	@ResponseBody
	public String resumeJob(@RequestParam String name,String group) {
		try {
			quartzManager.resumeJob(name, group);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
