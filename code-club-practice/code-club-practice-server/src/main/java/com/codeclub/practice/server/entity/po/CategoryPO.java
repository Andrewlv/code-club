package com.codeclub.practice.server.entity.po;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPO {

    private Long id;

    private String categoryName;

    private Integer categoryType;

    private Long parentId;

}