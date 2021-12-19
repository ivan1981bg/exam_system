package project.exam_system.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import project.exam_system.service.LogService;

import java.security.Principal;

@Aspect
@Component
public class LogAspect {
    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* project.exam_system.web.controllers.ExamController.onCompletion(..))")
    public void completionPointcut(){

    };

    @After("completionPointcut()")
    public void afterAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        Long examId = (Long) args[0];
        Principal principal =(Principal) args[1];
        String username = principal.getName();

        logService.createLog(username, examId);
    }
}
