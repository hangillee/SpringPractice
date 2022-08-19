package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    //Dependency Injection : 의존관계 주입 Impl 클래스들이 DIP를 준수할 수 있게 해준다.
    //생성자 주입 MemberServiceImpl은 이제 추상화(인터페이스)에만 의존한다.
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    //생성자 주입 OrderServiceImpl은 이제 추상화(인터페이스)에만 의존한다.
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
