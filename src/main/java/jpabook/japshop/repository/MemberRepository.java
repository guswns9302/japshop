package jpabook.japshop.repository;

import jpabook.japshop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    // 엔티티 매니저 주입
    @PersistenceContext
    private EntityManager em;

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
