package aoptest;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OutPut {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(ConcertConfig.class);
        NewPerform newPerform = (NewPerform) context.getBean("newPerform");
        newPerform.perform();
    }
}
