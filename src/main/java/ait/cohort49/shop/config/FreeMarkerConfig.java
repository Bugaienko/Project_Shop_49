package ait.cohort49.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

// @Configuration -ручная конфигурация
public class FreeMarkerConfig {

    @Bean
    public freemarker.template.Configuration freeMarkerConfig() {
        freemarker.template.Configuration config = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_32);
        config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/mail");
        config.setDefaultEncoding("UTF-8");
        return config;
    }
}
