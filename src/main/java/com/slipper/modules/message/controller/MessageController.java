package com.slipper.modules.message.controller;

import com.slipper.common.pojo.PageResult;
import com.slipper.common.pojo.Result;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.message.service.MessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult<MessageResVO>> page(@Validated MessagePageReqVO reqVO) {
        return Result.success(
                messageService.page(reqVO)
        );
    }

}
