package com.codeclub.wx.handler;

import com.codeclub.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ReceiveTextMsgHandler implements WeChatMsgHandler {

    @Resource
    private RedisUtil redisUtil;

    private static final String KEY_WORD = "验证码";

    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        log.info("接收到用户文本消息事件");
        String content = msgMap.get("Content");
        if (!KEY_WORD.equals(content)) {
            return "";
        }
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");

        Random random = new Random();
        int num = random.nextInt(1000);
        String numKey = redisUtil.buildKey(fromUserName, String.valueOf(num));
        redisUtil.setNx(numKey, "1", 5L, TimeUnit.MINUTES);
        String numContent = "您当前的验证码是：" + num + "，5分钟内有效";
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + numContent + "]]></Content>\n" +
                "</xml>";
        return replyContent;
    }
}
