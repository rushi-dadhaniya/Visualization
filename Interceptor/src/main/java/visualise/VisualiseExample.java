package visualise;

import kafka.VisualizationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import kafka.VisualizationProducerConfig;

@Configuration
public class VisualiseExample {

    @Autowired
    private static MyServiceImpl myService;
    private VisualizationProducer visualizationProducer;
    private static String topic = "maas360.ios.visualization.topic";
//    @Visualise(reqId = 123)
    public static void method1() {
        System.out.println("Inside method1");
    }

    public static void main(String []args) throws Exception {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(VisualizationProducerConfig.class);
        myService = context.getBean(MyServiceImpl.class);
        VisualizationProducer visualizationProducer = context.getBean(VisualizationProducer.class);
        visualizationProducer.visualize(topic, "abc");
        method1();
        myService.doSomething(5);
    }

}
