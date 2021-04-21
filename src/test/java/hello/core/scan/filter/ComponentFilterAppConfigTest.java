package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanA beanA = applicationContext.getBean("beanA",BeanA.class);
        assertThat(beanA).isNotNull();

        applicationContext.getBean("beanB",BeanB.class);
        assertThrows(
                NoSuchBeanDefinitionException.class,() -> applicationContext.getBean("beanB",BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type =  FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type =  FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )

    static class ComponentFilterAppConfig{

    }
}
