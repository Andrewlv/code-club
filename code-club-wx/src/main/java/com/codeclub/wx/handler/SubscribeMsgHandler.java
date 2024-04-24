package com.codeclub.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SubscribeMsgHandler implements WeChatMsgHandler {
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        log.info("触发用户关注事件");
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");
        String subscribeContent = "感谢您的关注";
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + subscribeContent + "]]></Content>\n" +
                "</xml>";
        return content;
    }
}
