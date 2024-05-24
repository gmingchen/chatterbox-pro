package com.slipper.common.utils;

import com.slipper.common.enums.EnvEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 获取 bean 工具
 * @author gumingchen
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(clazz) : null;
    }

    public static <T> T getBean(String bean, Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(bean, clazz) : null;
    }

    /**
     * 环境判断
     * @param env 环境
     * @return
     */
    public static boolean envJudge(EnvEnum env) {
        Environment environment = applicationContext.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (activeProfile.equals(env.getCode())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 是否是开发环境
     * @return
     */
    public static boolean isDevelopment() {
        return envJudge(EnvEnum.DEV);
    }
    /**
     * 是否是测试环境
     * @return
     */
    public static boolean isTest() {
        return envJudge(EnvEnum.TEST);
    }
    /**
     * 是否是生产环境
     * @return
     */
    public static boolean isProduction() {
        return envJudge(EnvEnum.PROD);
    }
}
