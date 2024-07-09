package com.web.yzh.config;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public TimedCache<String, String> simpleCacheManager(){
        //创建缓存，默认4毫秒过期


        return  new TimedCache<String, String>(60000);
    }
}
