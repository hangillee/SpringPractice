package hello.core.member;

//MemberService 인터페이스의 구현체 MemoryServiceImpl(Implements)
public class MemberServiceImpl implements MemberService{
    //다형성에 의해 MemberRepository 인터페이스가 아닌
    //구현체 MemoryMemberRepository의 메소드들이 오버라이딩 되어 사용 가능해진다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //문제는 이 구현체는 MemberRepository 인터페이스와 MemoryMemberRepository까지 의존한다!
    //SOLID 중 DIP를 위반하과 있다.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
