package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//이것이 DI Container!
@Configuration
public class AppConfig {
    //Dependency Injection : 의존관계 주입 Impl 클래스들이 DIP를 준수할 수 있게 해준다.
    //생성자 주입 MemberServiceImpl은 이제 추상화(인터페이스)에만 의존한다.
    @Bean //Spring 컨테이너에 등록됨
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    //생성자 주입 OrderServiceImpl은 이제 추상화(인터페이스)에만 의존한다.
    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //역할을 좀 더 세세하게 구분했다.
    //중복이 제거 되었고 변경이 용이하게 되었다.
    //이제 역할과 구현 클래스가 한 눈에 들어온다.
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
