package com.slipper.modules.expression.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.expression.model.res.ExpressionSelectResVO;
import com.slipper.modules.expression.service.ExpressionService;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表情
 * @author gumingchen
 */
@RestController
@RequestMapping("/expression")
public class ExpressionController {

    @Resource
    private ExpressionService expressionService;

    /**
     * 获取表情选择列表
     * @return
     */
    @Repeat()
    @GetMapping("/select")
    public Result<List<ExpressionSelectResVO>> selectList() {
        return Result.success(
                expressionService.selectList()
        );
    }
}
