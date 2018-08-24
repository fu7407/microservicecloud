package com.zzf.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 不能放在@ComponentScan所扫描的当前包下以及子包下，否则自定义的配置类会被所有的Ribbon客户端所共享，就达不到特殊化的定制目的了
 * @author zhangzengfu
 *
 */
@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule() {
		return new RandomRule();// 采用随机算法（默认是轮询算法RoundRobinRule）
		// return new RandomRule_ZY();// 自定义的规则，每个访问5次，再下一个
	}
}
