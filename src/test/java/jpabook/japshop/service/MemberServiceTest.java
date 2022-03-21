package jpabook.japshop.service;

import jpabook.japshop.domain.Member;
import jpabook.japshop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할때 스프링과 같이 실행
@SpringBootTest // 스프링 컨테이너 안에서 테스트 실행
@Transactional // 테스트에서 사용할 땐 롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //WHEN
        Long savedId = memberService.join(member);

        //THEN
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_에외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //WHEN
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 됨

        //THEN
        fail("예외가 발생해야 한다.");
    }
}