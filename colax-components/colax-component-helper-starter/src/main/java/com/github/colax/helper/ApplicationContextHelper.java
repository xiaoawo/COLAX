package com.github.colax.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component("colaCatchLogApplicationContextHelper")
@Slf4j
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        T bean = null;
        // 优先按type查
        try {
            bean = (T) applicationContext.getBean(clazz);
        } catch (Exception e) {
        }

        // 按name查
        try {
            if (bean == null) {
                String simpleName = clazz.getSimpleName();
                //首字母小写
                simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
                bean = (T) applicationContext.getBean(simpleName);
            }
        }
        catch (Exception e) {
            log.warn("No bean found for " + clazz.getCanonicalName());
        }
        return bean;
    }

    public static Object getBean(String name) {
        return ApplicationContextHelper.applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ApplicationContextHelper.applicationContext.getBean(name, requiredType);
    }
}
