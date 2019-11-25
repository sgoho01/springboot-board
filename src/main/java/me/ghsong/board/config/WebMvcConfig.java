package me.ghsong.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-13
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/css/**",
                "/vendor/**",
                "/img/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/static/resources/css/",
                        "classpath:/static/resources/vendor/",
                        "classpath:/static/resources/img/",
                        "classpath:/static/resources/js/");
    }
}
