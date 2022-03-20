package jpabook.japshop.repository;

import jpabook.japshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /*
    // 엔티티 매니저 주입
    @PersistenceContext // -> 스프링 부트에서는 이 어노테이션을 @Autowired로 사용하게 해줌
    private EntityManager em;
    // 따라서 롬복을 적용시켜 RequiredArgsContructor을 사용하면 코드를 간결하고 통일성 있게 할 수 있음
     */
    private final EntityManager em;

    // 멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // 전체 조회
   public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 찾기
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
