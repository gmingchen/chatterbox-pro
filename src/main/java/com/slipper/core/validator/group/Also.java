package com.slipper.core.validator.group;

import javax.validation.GroupSequence;

/**
 * 校验顺序 并且关系
 * @author gumingchen
 */
@GroupSequence({Create.class, Update.class})
public interface Also {
}
