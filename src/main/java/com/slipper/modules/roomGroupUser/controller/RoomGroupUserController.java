package com.slipper.modules.roomGroupUser.controller;

import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/roomGroupUser")
public class RoomGroupUserController {

    @Resource
    private RoomGroupUserService roomGroupUserService;

}
