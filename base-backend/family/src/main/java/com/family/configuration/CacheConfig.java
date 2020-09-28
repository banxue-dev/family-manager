package com.family.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

	/**
	 * 验证码catch
	 * @return
	 */
    @Bean(name = "verifyCode")
    public Cache<String, String> verifyCache() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(20, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000)
                .build();
    }
    @Bean(name = "authCache")
    public Cache<String, String> authCache() {
    	return Caffeine.newBuilder()
    			// 设置最后一次写入或访问后经过固定时间过期
    			.expireAfterWrite(20, TimeUnit.SECONDS)
    			// 初始的缓存空间大小
    			.initialCapacity(100)
    			// 缓存的最大条数
    			.maximumSize(1000)
    			.build();
    }

}