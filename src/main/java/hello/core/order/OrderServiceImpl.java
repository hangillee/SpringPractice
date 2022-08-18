package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //DiscountPolicy라는 추상 역할 뿐만 아니라 구현체인 Fix, RateDiscountPolicy까지 의존한다. (알고 있다)
    //이는 의존관계 역전 원칙, DIP를 준수하지 못한 것이다.
    //또한 구현체를 갈아 끼울 때 OrderServiceImpl(클라이언트)의 코드를 수정했으므로 개방 폐쇄 원칙, OCP 역시 준수하지 못했다.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;

    //단일 책임 원칙은 잘 준수했기에 주문(Order)과 할인(DiscountPolicy)이 수정할 때 서로 영향이 없다.
    //만약 주문 역할에 할인까지 구현했다면 할인 정책이 변경되어도 주문을 수정해야하니 이는 좋은 객체 지향이 될 수 없다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
