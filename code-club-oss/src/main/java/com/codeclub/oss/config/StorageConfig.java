package com.codeclub.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.codeclub.oss.adapter.AliStorageAdapter;
import com.codeclub.oss.adapter.MinioStorageAdapter;
import com.codeclub.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件存储config
 */
@Configuration
@RefreshScope
public class StorageConfig {


    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的文件存储适配器");
        }
    }

}
