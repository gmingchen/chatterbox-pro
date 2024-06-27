package com.slipper.modules.auth.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * QQ OpenId
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class QqOpenIdDTO {
    /**
     * appId
     */
    private String clientId;
    /**
     * openId
     */
    private String openid;
}
