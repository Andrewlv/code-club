package com.codeclub.auth.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回实体
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * 当前页码
     */
    private Integer pageNo = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 20;

    /**
     * 总记录数
     */
    private Integer total = 0;

    /**
     * 总页数
     */
    private Integer totalPages = 0;
    private List<T> result = Collections.emptyList();

    /**
     * 当前页第一条记录的位置
     */
    private Integer start = 1;

    /**
     * 当前页最后一条记录的位置
     */
    private Integer end = 0;

    public void setRecords(List<T> result) {
        this.result = result;
        if (result != null && !result.isEmpty()) {
            setTotal(result.size());
        }
    }

    public void setTotal(Integer total) {
        this.total = total;
        if (pageSize > 0) {
            // 计算总页数
            totalPages = (total / pageSize) + (total % pageSize == 0 ? 0 : 1);
        } else {
            totalPages = 0;
        }
        start = (pageSize > 0 ? (pageNo - 1) * pageSize : 0) + 1;
        end = (start - 1 + pageSize * (pageNo > 0 ? 1 : 0));
    }

}
