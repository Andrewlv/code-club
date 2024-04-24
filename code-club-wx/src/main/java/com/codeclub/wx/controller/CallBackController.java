package com.codeclub.wx.controller;

import com.codeclub.wx.utils.MessageUtil;
import com.codeclub.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class CallBackController {

    private static final String token = "sadasfds";

    @RequestMapping("test")
    public String test() {
        return "hello world";
    }

    /**
     * 回调消息验签
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }

    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信的请求：requestBody:{}, signature:{}, timestamp:{}, nonce:{}", requestBody, signature, timestamp, nonce);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");

        String msg = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好，我是小黑子]]></Content>\n" +
                "  <MsgId>1234567890123456</MsgId>\n" +
                "  <MsgDataId>xxxx</MsgDataId>\n" +
                "  <Idx>xxxx</Idx>\n" +
                "</xml>";
        return msg;
    }

}
