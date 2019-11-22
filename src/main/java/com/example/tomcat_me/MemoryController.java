package com.example.tomcat_me;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jiaohongtao
 * @decription
 * @since 2019/11/20 15:55
 */
@Controller
public class MemoryController {

	private static List<Object> list = new LinkedList<>();

	@RequestMapping("/increaseInput")
	@ResponseBody
	public String increaseInput(long input) {

		for (int i = 0; i < input; i++) {
			list.add(new Object());
		}
		getMemory();
		return "增加使用内存(2M)成功";
	}


	@RequestMapping("/increaseTwo")
	@ResponseBody
	public Object increaseTwo() {

		for (int i = 0; i < 50000; i++) {
			list.add(new Object());
		}

		JSONObject object = new JSONObject();
		object.put("sign", "增加使用内存(2M)成功");
		object.put("memory", getMemory());
		return object;
	}

	@RequestMapping("/increaseTwenty")
	@ResponseBody
	public Object increaseTwenty() {

		for (int i = 0; i < 500000; i++) {
			list.add(new Object());
		}

		JSONObject object = new JSONObject();
		object.put("sign", "增加使用内存(2M)成功");
		object.put("memory", getMemory());
		return object;
	}

	@RequestMapping("/increaseTwoHundred")
	@ResponseBody
	public Object increaseTwoHundred() {

		for (int i = 0; i < 5000000; i++) {
			list.add(new Object());
		}

		JSONObject object = new JSONObject();
		object.put("sign", "增加使用内存(2M)成功");
		object.put("memory", getMemory());
		return object;
	}

	@RequestMapping("/releaseMe")
	@ResponseBody
	public Object releaseMe() {

		list.clear();
		System.gc();
		JSONObject object = new JSONObject();
		object.put("memory", getMemory());
		object.put("sign", "内存释放成功");
		return object;
	}

	@RequestMapping("/decrease")
	@ResponseBody
	public String decrease() {

		for (int i = 0; i < 50000; ) {
			if (list.size() > 0) {
				synchronized (this) {
					list.remove(i);
				}
			} else {
				return "已到达初始内存";
			}
			i++;
		}
		getMemory();
		return "降低使用内存成功";
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	private Object getMemory() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

		MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //堆内存使用情况

		long totalMemorySize = memoryUsage.getInit(); //初始的总内存
		long maxMemorySize = memoryUsage.getMax(); //最大可用内存
		long usedMemorySize = memoryUsage.getUsed(); //已使用的内存

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("TotalMemory", totalMemorySize / (1024 * 1024) + "M");
		// jsonObject.put("FreeMemory", (totalMemorySize - usedMemorySize) / (1024 * 1024) + "M");
		jsonObject.put("FreeMemory", (maxMemorySize - usedMemorySize) / (1024 * 1024) + "M");
		jsonObject.put("MaxMemory", maxMemorySize / (1024 * 1024) + "M");
		jsonObject.put("UsedMemory", usedMemorySize / (1024 * 1024) + "M");

		return jsonObject;
//		System.out.println("TotalMemory: " + totalMemorySize / (1024 * 1024) + "M");
//		System.out.println("FreeMemory: " + (totalMemorySize - usedMemorySize) / (1024 * 1024) + "M");
//		System.out.println("MaxMemory: " + maxMemorySize / (1024 * 1024) + "M");
//		System.out.println("UsedMemory: " + usedMemorySize / (1024 * 1024) + "M");
//		System.out.println("--------------------------------------");
	}
}
