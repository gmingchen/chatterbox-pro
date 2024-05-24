package com.slipper.modules.roomFriend.controller;

import com.slipper.modules.roomFriend.service.RoomFriendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/roomFriend")
public class RoomFriendController {

    @Resource
    private RoomFriendService roomFriendService;

}
