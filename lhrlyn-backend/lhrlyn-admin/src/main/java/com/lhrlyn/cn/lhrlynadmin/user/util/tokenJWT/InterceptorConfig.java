package com.lhrlyn.cn.lhrlynadmin.user.util.tokenJWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  全局配置拦截器
 * @author lhr
 * @date  2021/12/30 11:03 PM
 * @param
 * @return:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJwtInterceptor()).
                excludePathPatterns("/user/**")
                .addPathPatterns("/**");
    }
    @Bean
    public JwtTokenInterceptor getJwtInterceptor(){
        return new JwtTokenInterceptor();
    }

}
