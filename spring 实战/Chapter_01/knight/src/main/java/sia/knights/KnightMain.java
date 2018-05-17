package sia.knights;

import org.springframework.context.support.
                   ClassPathXmlApplicationContext;

public class KnightMain {

  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext(
            "META-INF/spring/knight.xml");
    Knight knight = context.getBean(Knight.class);
    System.out.println(knight.getClass());
//    knight.embarkOnQuest();
//    context.close();
//    System.out.println(Knight.class);
  }

}
