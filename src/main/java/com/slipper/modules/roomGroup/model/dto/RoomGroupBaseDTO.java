package com.slipper.modules.roomGroup.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class RoomGroupBaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 群名
     */
    private Long name;
    /**
     * 群头像
     */
    private Long avatar;
}
