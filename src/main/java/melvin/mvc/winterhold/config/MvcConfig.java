package melvin.mvc.winterhold.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home/home");
        registry.addViewController("/login").setViewName("forward:/account/login-form");
        registry.addViewController("/register").setViewName("forward:/account/reigster-form");
//        registry.addViewController("/account").setViewName("forward:/account/index");
        registry.addViewController("/access-denied").setViewName("forward:/account/access-denied");
        registry.addViewController("/author").setViewName("forward:/author/index");
        registry.addViewController("/category").setViewName("forward:/category/index");
        registry.addViewController("/customer").setViewName("forward:/customer/index");
        registry.addViewController("/loan").setViewName("forward:/loan/index");
    }
}
