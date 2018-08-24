package com.zzf.springcloud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zzf.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeptClientServiceFallbackFactory.class);

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {

			@Override
			public Dept get(Long id) {
				// 日志最好放在fallback方法中，不要直接放在create方法中，否则在引用启动时就会打印该日志
				DeptClientServiceFallbackFactory.LOGGER.info("fallback; reason is:", cause);
				Dept dept = new Dept();
				dept.setDeptno(id);
				dept.setDname("该ID：" + id + "没有对应的信息,consumer客户端提供的降级信息，此刻服务provicer已经关闭");
				dept.setDb_source("no this database in mysql ");
				return dept;
			}

			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}

		};
	}

}
