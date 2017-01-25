package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.FooService;
import services.FooServiceImpl;

@Configuration
@ComponentScan(basePackages = {"controllers", "services"})
public class AppConf
{
    @Bean
    public FooService fooService() {
        return new FooServiceImpl();
    }
}