package learn.unit.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SomeAspect {
    @Pointcut(value = "@annotation(learn.unit.test.aspect.SomeAnnotation)")
    public void pointcut() {}

    @After(value = "pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        log.error("in AOP SomeAspect");
    }
}
