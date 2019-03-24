package com.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.quartz.entity.CronEntity;

/**
 * 
* @ClassName: CronMapper 
* @Description: mapper
* @author Mr.zhou 
* @date Mar 22, 2019 9:58:48 PM
 */
@Mapper
public interface CronMapper {
	/**
	* @Title: load 
	* @Description: 根据id查询
	* @param @param id
	* @param @return    设定文件 
	* @return CronEntity    返回类型 
	* @throws
	 */
	@Select("select * from cron_table where id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cron", column = "cron"),
            @Result(property = "quarzName", column = "quarz_name"),
            @Result(property = "schedulerClass", column = "scheduler_class"),
            @Result(property = "time", column = "time")
    })
	CronEntity load(String id);
	/**
	* @Title: insert 
	* @Description: 添加定时任务
	* @param @param cronEntity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@Insert("insert into cron_table(id,user_id,cron,quarz_name,scheduler_class,time)" +
            "values(#{id},#{userId},#{cron},#{quarzName},#{schedulerClass},#{time})")
    void insert(CronEntity cronEntity);
	/**
	* @Title: queryAll 
	* @Description: 查询全部
	* @param @return    设定文件 
	* @return List<CronEntity>    返回类型 
	* @throws
	 */
    @Select("select * from cron_table")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cron", column = "cron"),
            @Result(property = "quarzName", column = "quarz_name"),
            @Result(property = "schedulerClass", column = "scheduler_class"),
            @Result(property = "time", column = "time")
    })
    List<CronEntity> queryAll();
    /**
    * @Title: updateCron 
    * @Description: 根据ID修改
    * @param @param id
    * @param @param cron    设定文件 
    * @return void    返回类型 
    * @throws
     */
    @Update("update cron_table set cron=#{arg0} where id =#{arg1}")
    void updateCron(String cron,String id);

}
