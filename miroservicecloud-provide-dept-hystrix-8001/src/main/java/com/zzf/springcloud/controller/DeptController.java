package com.zzf.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzf.springcloud.entities.Dept;
import com.zzf.springcloud.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	/** 出错调用fallbackMethod指定的方法，还可以设置
	 * commandProperties={@HystrixProperty(name="execution.isolation.strategy", value="THREAD")}//线程隔离THREAD（默认）  信号量隔离SEMAPHORE
	* commandProperties={@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="10000")}
	* commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")}
	* threadPoolProperties={@HystrixProperty(name="coreSize", value="1"),@HystrixProperty(name="maxQueueSize", value="10")}
	* */
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有对应的信息");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDname("该ID：" + id + "没有对应的信息,null--@HystrixCommand");
		dept.setDb_source("no this database in mysql ");
		return dept;
	}

}
