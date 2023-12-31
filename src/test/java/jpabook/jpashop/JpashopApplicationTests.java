package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JpashopApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@Transactional
	@Rollback(value = false)
	public void testMember() throws Exception{
	    //given
//		Member member = new Member();
//		member.setName("memberA");
//
//	    //when
//		Long saveId = memberRepository.save(member);
//		Member findMember = memberRepository.find(saveId);
//
//		//then
//		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//		Assertions.assertThat(findMember).isEqualTo(member); // 같은 트랜잭션 안에서 영속성컨텍스트에서 식별이 같으면 같음

		// 배포
		// 1. ./gradlew clean build
		// 2. java -jar jpashop-0.0.1-SNAPSHOT.jar

	}
}
