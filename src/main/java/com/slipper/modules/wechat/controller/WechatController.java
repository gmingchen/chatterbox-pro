package com.slipper.modules.wechat.controller;

import com.slipper.core.wechat.config.WechatConfig;
import com.slipper.core.wechat.utils.WechatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Resource
    private WechatConfig wechatConfig;

    @GetMapping("/message")
    public String officialMessage(@RequestParam(required = false) String signature,
                                  @RequestParam(required = false) String timestamp,
                                  @RequestParam(required = false) String nonce,
                                  @RequestParam(required = false) String echostr) {
        boolean bool = WechatUtils.checkSignature(wechatConfig.getToken(), signature, timestamp, nonce);
        if (bool) {
            return echostr;
        }
        return null;
    }
}
