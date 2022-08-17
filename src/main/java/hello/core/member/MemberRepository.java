package hello.core.member;

//역할(인터페이스) : 회원의 정보를 저장하고 Id를 기준으로 찾는다.
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
