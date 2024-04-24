package com.codeclub.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WeChatMsgFactory implements InitializingBean {

    @Resource
    private List<WeChatMsgHandler> weChatMsgHandlerList;

    private Map<WeChatMsgTypeEnum, WeChatMsgHandler> handlerMap = new HashMap<>();

    public WeChatMsgHandler getHandlerByMsgType(String msgType) {
        WeChatMsgTypeEnum msgTypeEnum = WeChatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (WeChatMsgHandler weChatMsgHandler : weChatMsgHandlerList) {
            handlerMap.put(weChatMsgHandler.getMsgType(), weChatMsgHandler);
        }
    }
}
