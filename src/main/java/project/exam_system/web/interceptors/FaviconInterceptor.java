package project.exam_system.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FaviconInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        String link = "https://image.shutterstock.com/image-photo/notebook-question-are-you-ready-600w-1013125333.jpg";

        if (modelAndView != null) {
            modelAndView.addObject("favicon", link);
        }
    }
}
