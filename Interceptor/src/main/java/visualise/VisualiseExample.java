package visualise;

import kafka.VisualizationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import kafka.VisualizationProducerConfig;

@Configuration
public class VisualiseExample {

    private static MyServiceImpl myService;
    private static MyServiceImpl2 myService2;
    private VisualizationProducer visualizationProducer;
    private static String topic = "maas360.ios.visualization.topic.2";
//    @Visualise(reqId = 123)
    public static void method1() {
        System.out.println("Inside method1");
    }

    public static void main(String []args) throws Exception {
//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(VisualizationProducerConfig.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("visualiser-config.xml");
//        /Users/rushi/Documents/Project/MGR/2k18/Interceptor/src/main/java/config
        myService = context.getBean(MyServiceImpl.class);
        myService2 = context.getBean(MyServiceImpl2.class);
        VisualizationProducer visualizationProducer = context.getBean(VisualizationProducer.class);
        visualizationProducer.visualize(topic, "abc");
        method1();
        myService.doSomething(5, 10);
        myService2.print();
    }

}
