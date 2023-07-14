package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 컴포넌트 스캔해서 자동으로 빈 등록
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext // 스프링이 entityManager을 생성해서 자동으로 주입해줌
    // boot jpa 는 @RequiredArgsConstructor로 대체 가능
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        String query = "SELECT m FROM Member m ";
        return em.createQuery(query, Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        String query = "SELECT m FROM Member m WHERE m.name = :name";
        return em.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
