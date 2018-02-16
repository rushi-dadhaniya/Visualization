package visualise;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class VisualiseAspect {

    @Before("execution(* *(..)) && @annotation(Visualise)")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("before method");
    }

    //    @Around("execution(* visualise.*.*(..)) && @annotation(Visualise)")
//    @Around("annotationPointCutDefinition(visualise) && atExecution()")
    @Around("@annotation(visualise) && execution(* *(..))")
    public Object log(ProceedingJoinPoint joinPoint, Visualise visualise) throws Throwable {
        System.out.println("Inside logDuration");
        long start = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Method execution time: " + elapsedTime + " mili second." + "\n\n");
        String methodName = method.getName();
        System.out.println("method name is " + methodName);
        return method;
    }

    @AfterThrowing(value = "execution(* *.*(..))", throwing = "e")
    public void fetchError(JoinPoint joinPoint, Throwable e) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        System.out.println("We have caught exception in method: " + methodName + " with arguments " + arguments + " and the full toString: " + stuff + "the exception is: " + e.getMessage() + " " + e);
    }
}
