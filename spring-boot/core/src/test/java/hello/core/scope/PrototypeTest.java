package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);

        bean1.close();
        bean2.close();
        ac.close(); // 종료시 호출이 안되기 때문에 위에서 close()함수를 직접 호출해주어야 한다.
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("SingletonBean.close");
        }
    }
}
