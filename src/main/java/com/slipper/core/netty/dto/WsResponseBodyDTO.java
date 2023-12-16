package com.slipper.core.netty.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class WsResponseBodyDTO implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

}
