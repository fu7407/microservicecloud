package com.zzf.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zzf.springcloud.entities.Dept;
import com.zzf.springcloud.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}

	/**
	 * 启动类需添加@EnableDiscoveryClient
	 */
	@Autowired
	private DiscoveryClient client;

	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("***********" + list);
		List<ServiceInstance> srvList = client.getInstances("miroservicecloud-dept");
		for (ServiceInstance ele : srvList) {
			System.out.println(
					ele.getServiceId() + "\t" + ele.getHost() + "\t" + ele.getMetadata() + "\t" + ele.getUri());
		}
		return this.client;
	}

}
