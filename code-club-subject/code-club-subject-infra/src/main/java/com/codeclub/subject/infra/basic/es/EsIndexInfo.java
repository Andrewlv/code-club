package com.codeclub.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * es索引类
 */
@Data
public class EsIndexInfo implements Serializable {

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 索引名称
     */
    private String indexName;
}
