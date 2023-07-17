package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

// 완전 스프링이랑 인티그레이션해서 실행
@ExtendWith(SpringExtension.class) // @RunWith(SpringRunner.class) junit4
@SpringBootTest // 스프링 뛰운 상태에서 테스트 실행
@Transactional // 테스트에선 롤백 처리
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    //1. entity의 generatevalue 정책마다 다르지만, memberService.join 실행시
    // persist할 경우 커밋 전에는 쿼리를 던지지 않음. Test시 트랜잭션은 커밋보다 롤백이기 때문에.
    // 롤백설정을 꺼주면, 실행이 됨

    // 2. 롤백 설정없이 em.flush를 추가하면 됨

    @Test
    //@Rollback(false)  // DB에 삽입된거 보고싶으면 주석 해제
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then

        // 서비스 테스트 니깐 비교할 값을 repository에서 가져와야함.
        em.flush(); // persist 쿼리 날리기
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);


        //then
        IllegalStateException thrown =
                assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertEquals("이미 존재하는 회원입니다", thrown.getMessage());
    }

}