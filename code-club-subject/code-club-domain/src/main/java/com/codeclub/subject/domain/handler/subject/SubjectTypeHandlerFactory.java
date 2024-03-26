package com.codeclub.subject.domain.handler.subject;

import com.codeclub.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目策略工厂
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType) {
        SubjectInfoTypeEnum typeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(typeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler handler : subjectTypeHandlerList) {
            handlerMap.put(handler.getHandlerType(), handler);
        }
    }
}
