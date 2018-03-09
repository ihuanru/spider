package com.su.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author suyupeng
 */
public class ContextUtil {
    public static <T> T getBean(Class<T> clazz) {
        String[] fileUrl = new String[]{"classpath*:applicationContext.xml"};
        ApplicationContext appContext = new ClassPathXmlApplicationContext(fileUrl);
        return appContext.getBean(clazz);
    }
}
