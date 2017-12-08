package integration.com.fibanez.spring4basic.utils;

import com.fibanez.spring4basic.web.service.CustomerService;
import org.hibernate.validator.HibernateValidator;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fibanez.spring4basic.web.controller")
public class TestContext extends WebMvcConfigurerAdapter {

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
        return localValidatorFactory;
    }

    @Bean
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }

}
