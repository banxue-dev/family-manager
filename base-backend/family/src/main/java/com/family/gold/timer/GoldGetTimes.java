package com.family.gold.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.family.utils.HttpUtils;
import com.family.utils.StringUtils;

/**
 * 获取实时行情数据
 * 每3秒获取一次数据
 * 只保留最近20条
 * 只存于内存中，不持久化
 * @author tWX932595
 *
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class GoldGetTimes {

	private static Logger logger=LoggerFactory.getLogger(GoldGetTimes.class);
	public static List<String> data=new ArrayList<String>();
	private static int max=20;
	
	@Value("${goldtimesinterface}")
	private String url;
	/**
	 * 调用接口获取数据
	 */
	@Scheduled(fixedRate=10000)
    //@Scheduled(cron = "0 0/5 * * * ?")
	public  void doGetData() {
		try {

	    	long start=new Date().getTime();
	    	String soures=HttpUtils.sendGet(url, "");
	    	long end=new Date().getTime();
	    	if(StringUtils.isNotNull(soures) && !soures.contains("Exception")) {

				if(data.size()>=max) {
					//data.remove(data.size()-1);
					data.remove(0);
				}
//				data.add(0, soures);
				data.add(soures);
	    		//logger.debug("调用成功"+"时间："+(end-start)+"秒"+"--"+data.size());
	    	}else {
	    		logger.error("调用失败"+"时间："+(end-start)+"秒"+"--"+data.size()+""+soures);
	    	}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("异常："+e);
		}
	}
}
