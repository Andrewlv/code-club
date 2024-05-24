package com.codeclub.subject.infra.basic.es;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * es配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "es.cluster")
public class EsConfigProperties{

    private List<EsClusterConfig> esConfigs = new ArrayList<>();


}
