package com.slipper.core.mybatisplus.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.annotation.DataPermissionAlias;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据权限
 * @author gumingchen
 */
public class DataPermissionImplHandler implements DataPermissionHandler {
    /**
     * 用户ID字段
     */
    private static final String USER_COLUMN = "user_id";

    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        LoginUserDTO loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            Column column = getColumn(mappedStatementId, USER_COLUMN);
            EqualsTo equalsTo = new EqualsTo(column, new LongValue(loginUser.getId()));
            return new AndExpression(where, equalsTo);
        }
        return where;
    }


    /**
     * 获取列
     * @param mappedStatementId Mybatis MappedStatement Id 根据该参数可以判断具体执行方法
     * @return
     */
    private Column getColumn(String mappedStatementId, String columnName) {
        StringBuilder fullName = new StringBuilder();

        String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
        String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    DataPermissionAlias methodAnnotation = method.getAnnotation(DataPermissionAlias.class);
                    if (methodAnnotation != null && StringUtils.isNotBlank(methodAnnotation.value())) {
                        fullName.append(methodAnnotation.value()).append(".");
                    } else {
                        DataPermissionAlias classAnnotation = clazz.getAnnotation(DataPermissionAlias.class);
                        if (classAnnotation != null && StringUtils.isNotBlank(classAnnotation.value())) {
                            fullName.append(classAnnotation.value()).append(".");
                        }
                    }
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fullName.append(columnName);
        return new Column(fullName.toString());
    }

}
