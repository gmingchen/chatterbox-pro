package com.slipper.modules.applyUser.controller;

import com.slipper.modules.applyUser.service.ApplyUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户
 * @author gumingchen
 */
@RestController
@RequestMapping("/applyUser")
public class ApplyUserController {

    @Resource
    private ApplyUserService applyUserService;

}
