package aoptest;

import org.springframework.stereotype.Component;

@Component
public class NewPerform{

    public void perform(){
        System.out.println("perform()");
    }
}

