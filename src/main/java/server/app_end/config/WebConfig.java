package server.app_end.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 配置静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 /uploads/** 到项目根目录下的 uploads 文件夹
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

    // 配置 CORS 跨域支持
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOrigins("http://localhost:5173")  // 允许来自 http://localhost:5173 的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的 HTTP 请求方法
                .allowedHeaders("*")  // 允许所有请求头
                .allowCredentials(true)  // 允许携带 Cookies
                .maxAge(3600);  // 预检请求缓存时间（单位：秒）
    }
}
