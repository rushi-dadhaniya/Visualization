package visualise;

import kafka.JSONConverter;
import kafka.VisualizationPayload;
import kafka.VisualizationProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
public class VisualiseAspect {

    @Autowired
    @Qualifier("visualizationProducer")
    private VisualizationProducer visualizationProducer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String topic = "maas360.ios.visualization.topic";

    private ThreadLocal<String> threadLocal;

    @Before("@annotation(Visualise) && execution(* *(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        System.out.println("xxxxxxxxxxxxxx");
    }

    @Pointcut("@annotation(Visualise) && execution(* *(..))")
    public void businessMethods() {}

    @Around("@annotation(visualise)")
    public Object log(ProceedingJoinPoint joinPoint, Visualise visualise) throws Throwable {
        String reqId = null;
        if(threadLocal != null) {
            reqId = threadLocal.get();
        }
        else {
            threadLocal = new ThreadLocal<>();
            reqId = UUID.randomUUID().toString();
            threadLocal.set(reqId);
        }
        Long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        Long elapsedTime = System.currentTimeMillis() - startTime;
        VisualizationPayload visualizationPayload = new VisualizationPayload(reqId, visualise.workFlowName(), visualise.stage());
        visualizationPayload.setStatus("SUCCESS");
        visualizationPayload.setTimeRequired(elapsedTime);
        visualizationPayload.setTimestamp(new Timestamp(startTime).getTime());
        String message = JSONConverter.toJSON(visualizationPayload, VisualizationPayload.class);
        visualizationProducer.visualize(topic, message);
        return visualizationPayload;
    }

    @AfterThrowing(value = "@annotation(visualise) && execution(* *(..))", throwing = "e")
    public void fetchError(JoinPoint joinPoint, Throwable e, Visualise visualise) {
        Long startTime = System.currentTimeMillis();
        String reqId = threadLocal.get();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        System.out.println("We have caught exception in method: " + methodName + " with arguments " + arguments + " and the full toString: " + stuff + "the exception is: " + e.getMessage() + " " + e);
        VisualizationPayload visualizationPayload = new VisualizationPayload(reqId, visualise.workFlowName(), visualise.stage());
        visualizationPayload.setStatus("FAILED");
        visualizationPayload.setMethodArgs(arguments);
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        visualizationPayload.setErrorStack(sb.toString());
        visualizationPayload.setMessage(e.getMessage());
        visualizationPayload.setTimestamp(new Timestamp(startTime).getTime());
        String message = JSONConverter.toJSON(visualizationPayload, VisualizationPayload.class);

        visualizationProducer.visualize(topic, message);
    }
}
