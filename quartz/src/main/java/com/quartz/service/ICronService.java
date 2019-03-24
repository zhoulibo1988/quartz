package com.quartz.service;

import java.util.List;

import com.quartz.entity.CronEntity;


/**
 * 
* @ClassName: ICronService 
* @Description: 接口
* @author Mr.zhou 
* @date Mar 22, 2019 10:06:43 PM
 */
public interface ICronService {
	/**
	* @Title: sendEmail 
	* @Description: 发送邮件
	* @param @param quartzName    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void sendEmail(String quartzName);
	 /**
	  * 
	 * @Title: sendSms 
	 * @Description: 发送短信
	 * @param @param quartzName    设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
    void sendSms(String quartzName);
    /**
     * 
    * @Title: findByCronId 
    * @Description: 根据ID查询
    * @param @param id
    * @param @return    设定文件 
    * @return CronEntity    返回类型 
    * @throws
     */
    CronEntity findByCronId(String id );
    /**
    * @Title: update 
    * @Description: 修改定时任务信息
    * @param @param cronEntity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    void update(CronEntity cronEntity);
    /**
    * @Title: findAll 
    * @Description: 获取全部定时任务
    * @param @return    设定文件 
    * @return List<CronEntity>    返回类型 
    * @throws
     */
    List<CronEntity> findAll();
    /**
     * 
    * @Title: addCron 
    * @Description: 添加定时任务
    * @param @param cronEntity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    void addCron(CronEntity cronEntity);
}
