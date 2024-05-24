package com.slipper.modules.roomGroup.controller;

import com.slipper.modules.roomFriend.service.RoomFriendService;
import com.slipper.modules.roomGroup.service.RoomGroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/room")
public class RoomGroupController {

    @Resource
    private RoomGroupService roomGroupService;

}
