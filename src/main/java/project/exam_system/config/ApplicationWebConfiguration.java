package project.exam_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.exam_system.web.interceptors.FaviconInterceptor;
import project.exam_system.web.interceptors.TitleInterceptor;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {
    private final FaviconInterceptor faviconInterceptor;
    private final TitleInterceptor titleInterceptor;

    public ApplicationWebConfiguration(FaviconInterceptor faviconInterceptor, TitleInterceptor titleInterceptor) {
        this.faviconInterceptor = faviconInterceptor;
        this.titleInterceptor = titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(faviconInterceptor);
        registry.addInterceptor(titleInterceptor);
    }
}
