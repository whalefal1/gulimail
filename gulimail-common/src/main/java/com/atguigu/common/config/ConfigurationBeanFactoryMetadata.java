
package com.atguigu.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lingkang
 * Created by 2022/6/16
 *
 * 此类是为了兼容高版本与springcloud低版本依赖报错问题
 * springcloud中的oauth2依赖不能使用2.4.x以上版本，但springcloud的oauth2已经停止维护了
 * 需要在 配置文件中关闭检查：spring.cloud.compatibility-verifier.enabled=false
 *
 * - Change Spring Boot version to one of the following versions [2.2.x, 2.3.x] .
 * You can find the latest Spring Boot versions here [https://spring.io/projects/spring-boot#learn].
 * If you want to learn more about the Spring Cloud Release train compatibility, you can visit this page [https://spring.io/projects/spring-cloud#overview] and check the [Release Trains] section.
 * If you want to disable this check, just set the property [spring.cloud.compatibility-verifier.enabled=false]
 */
@Component
public class ConfigurationBeanFactoryMetadata implements ApplicationContextAware {
    public static final String BEAN_NAME = ConfigurationBeanFactoryMetadata.class.getName();
    private ConfigurableApplicationContext applicationContext;

    public ConfigurationBeanFactoryMetadata() {
    }

    public <A extends Annotation> Map<String, Object> getBeansWithFactoryAnnotation(Class<A> type) {
        Map<String, Object> result = new HashMap();
        String[] var3 = this.applicationContext.getBeanFactory().getBeanDefinitionNames();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String name = var3[var5];
            if (this.findFactoryAnnotation(name, type) != null) {
                result.put(name, this.applicationContext.getBean(name));
            }
        }

        return result;
    }

    public <A extends Annotation> A findFactoryAnnotation(String beanName, Class<A> type) {
        Method method = this.findFactoryMethod(beanName);
        return method != null ? AnnotationUtils.findAnnotation(method, type) : null;
    }

    public Method findFactoryMethod(String beanName) {
        ConfigurableListableBeanFactory beanFactory = this.applicationContext.getBeanFactory();
        if (beanFactory.containsBeanDefinition(beanName)) {
            BeanDefinition beanDefinition = beanFactory.getMergedBeanDefinition(beanName);
            if (beanDefinition instanceof RootBeanDefinition) {
                return ((RootBeanDefinition) beanDefinition).getResolvedFactoryMethod();
            }
        }

        return null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    static void register(BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(ConfigurationBeanFactoryMetadata.class);
            definition.setRole(2);
            registry.registerBeanDefinition(BEAN_NAME, definition);
        }

    }
}

