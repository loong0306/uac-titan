package me.uac.config;

import me.uac.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * <p>Title: WebMvcConfig. </p>
 * <p>Description MVC配置 </p>
 * @author dragon
 * @date 2018/3/29 下午5:06
 */
@Configuration
@EnableWebMvc
@Import(SwaggerConfig.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private AuthInterceptor authInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		super.addInterceptors(registry);
		registry.addInterceptor(authInterceptor).addPathPatterns("/api/uac/**");
	}

}
