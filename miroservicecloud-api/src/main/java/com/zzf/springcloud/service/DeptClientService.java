package com.zzf.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.springcloud.entities.Dept;

//@FeignClient(value = "MIROSERVICECLOUD-DEPT")
@FeignClient(value = "MIROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id);

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list();

	/**
	 * 在《Spring Cloud与Docker微服务架构实战》一书100页处有提到
	 * 此处多参数传递时需要加注解@RequestBody，调用该方法的controller的传入也用@RequestBody
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(Dept dept);
}
