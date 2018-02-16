package visualise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan({"visualise"})
public class VisualiseExample {

    @Autowired
    private static MyServiceImpl myService;

//    @Visualise(reqId = 123)
    public static void method1() {
        System.out.println("Inside method1");
    }

    public static void main(String []args) throws Exception {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(VisualiseConfig.class);
        myService = context.getBean(MyServiceImpl.class);
        method1();
        myService.doSomething(5);
    }

}
