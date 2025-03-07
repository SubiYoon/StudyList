package study.querydsl.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception {
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        // when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        Member findMember2 = memberJpaRepository.findByUsername("member1").get(0);
        List<Member> allMember = memberJpaRepository.findAll();

        // then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember2).isEqualTo(member);
        assertThat(allMember).contains(member);
    }

    @Test
    public void basicQuerydslTest() throws Exception {
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        // when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        Member findMember2 = memberJpaRepository.findByUsername_Querydsl("member1").get(0);
        List<Member> allMember = memberJpaRepository.findAll_Querydsl();

        // then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember2).isEqualTo(member);
        assertThat(allMember).contains(member);
    }

    @Test
    public void searchTest() throws Exception {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        // when
        List<MemberTeamDto> result = memberJpaRepository
                .search(condition);

        // then
        assertThat(result).extracting("username").containsExactly("member4");
    }
}