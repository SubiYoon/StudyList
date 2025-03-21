package hello.core.sigleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 호출시 매번 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 호출시 매번 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른 것을 확인
        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // override를 한 equals메서드를 사용하는 것과 같다.
        assertThat(singletonService1).isEqualTo(singletonService2);
        // same 은 == 로 비교하는 것과 같다.
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void springContainer() {
        ApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 호출시 매번 객체를 생성
        MemberService memberService1 = appConfig.getBean("memberService", MemberService.class);

        // 2. 호출시 매번 객체를 생성
        MemberService memberService2 = appConfig.getBean("memberService", MemberService.class);

        // 3. 참조값이 다른 것을 확인
        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
