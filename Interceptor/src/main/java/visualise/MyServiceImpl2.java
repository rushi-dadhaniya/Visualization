package visualise;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl2 {

    @Visualise(workFlowName = "AsmSync", stage = "stage2")
    public void print() {
        System.out.println("Inside MyServiceImpl2.print");
    }

}
