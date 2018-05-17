package soundsystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.awt.AWTAccessor;

public class CDPlayerMain {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
//        CDPlayer cdPlayer = context.getBean(CDPlayer.class);
//        CDPlayer cdPlayer = (CDPlayer) context.getBean("cDPlayer");
//        cdPlayer.play();
//        MediaPlayer mediaPlayer = (MediaPlayer) context.getBean("mediaPlayer");
//        mediaPlayer.play();
//        System.out.println(CDPlayer.class);
        SgtPeppers sgtPeppers = context.getBean(SgtPeppers.class);
        sgtPeppers.play();

    }
}
