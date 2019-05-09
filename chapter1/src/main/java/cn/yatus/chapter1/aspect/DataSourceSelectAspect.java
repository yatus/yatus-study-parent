package cn.yatus.chapter1.aspect;

import cn.yatus.chapter1.annotation.Master;
import cn.yatus.chapter1.annotation.Slave;
import cn.yatus.chapter1.config.DSContextHolder;
import cn.yatus.chapter1.config.DSRouteTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DataSourceSelectAspect {
    @Pointcut("@annotation(cn.yatus.chapter1.annotation.Master) " +
            "|| @annotation(cn.yatus.chapter1.annotation.Slave)" +
            "|| @annotation(org.springframework.transaction.annotation.Transactional)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();

        Slave slave = method.getAnnotation(Slave.class);
        if (slave != null) {
            DSContextHolder.slave();
            return;
        }

        Transactional transactional = method.getAnnotation(Transactional.class);
        if (transactional != null && transactional.readOnly()) {
            DSContextHolder.slave();
            return;
        }

        Master master = method.getAnnotation(Master.class);
        if (master != null) {
            DSContextHolder.master();
            return;
        }

        if (transactional != null && !transactional.readOnly()) {
            DSContextHolder.master();
            return;
        }

        DSContextHolder.slave();
    }
}
