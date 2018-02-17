package visualise;


import org.springframework.stereotype.Service;


@Service
public class MyServiceImpl {

    static {
        System.out.println("inside MyServiceImpl");
    }
    @Visualise(workFlowName = "AsmSync", stage = "stage1")
    public void doSomething(int a, int b) throws Exception {
        System.out.println("Inside service");
//        throw new Exception("Something is wrong here");
    }
}
