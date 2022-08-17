package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//MemberRepository 인터페이스(역할)의 구현체
public class MemoryMemberRepository implements MemberRepository {
    //회원의 정보를 로컬 메모리에 저장할 때 사용할 Map 자료형
    private static Map<Long, Member> store = new HashMap<>();

    //MemberRepository 인터페이스에 설계한 메소드를 구현 (오버라이딩)
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
