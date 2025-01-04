package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            Object bean = context.getBean(definitionName);
            System.out.println("name = " + definitionName + " object = " + bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(definitionName);

            //
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = context.getBean(definitionName);
                System.out.println("name = " + definitionName + " object = " + bean);
            }
        }
    }
}
