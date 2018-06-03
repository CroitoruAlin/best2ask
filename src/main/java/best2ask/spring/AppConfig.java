package best2ask.spring;

import best2ask.configure.ActiveUsersStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ActiveUsersStore activeUserStore(){
        return new ActiveUsersStore();
    }
}
