package me.uac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Title: MyCorsConfiguration. </p>
 * <p>Description 跨域配置 </p>
 * @author dragon
 * @date 2018/3/29 下午10:10
 */
@Configuration
public class MyCorsConfig {


    /**
     * <p>addMapping 接口格式 </p>
     * <p>allowedOrigins 允许跨域的请求地址 </p>
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins();
            }
        };
    }
}
