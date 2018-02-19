package visualise;

import kafka.VisualizationProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.Test;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("visualiser-config.xml");
        myService = context.getBean(MyServiceImpl.class);
        myService2 = context.getBean(MyServiceImpl2.class);
        VisualizationProducer visualizationProducer = context.getBean(VisualizationProducer.class);
        method1();
        myService.doSomething(5, 10);
        Test test = context.getBean(Test.class);
        test.doNothing();
        myService2.print();
    }

}
