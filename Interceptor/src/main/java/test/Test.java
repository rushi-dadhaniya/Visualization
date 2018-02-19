package test;

import org.springframework.stereotype.Component;
import visualise.Visualise;

@Component
public class Test {

    @Visualise(workFlowName = "test1", stage = "123")
    public void doNothing() {
        System.out.println("I am doing nothing");
    }
}
