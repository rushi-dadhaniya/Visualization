package visualise;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;


@Component
public class MyServiceImpl {

    static {
        System.out.println("inside MyServiceImpl");
    }
    @Visualise(reqId = 123)
    public void doSomething(int a) throws Exception {
        System.out.println("Inside service");
//        throw new Exception("Something is wrong here");
    }
}
