package soundsystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/soundsystem/app.properties")
public class CDPlayerConfig {
  
//  @Bean
//  public CompactDisc compactDisc() {
//    return new SgtPeppers();
//  }

  @Autowired
  Environment env;

  @Bean
  public CompactDisc compactDisc(){
    return new BlankDisc(
            env.getProperty("disc.title"),
            env.getProperty("disc.artist")
    );
  }
  
  @Bean
  public CDPlayer cdPlayer() {
    return new CDPlayer(compactDisc());
  }

}
