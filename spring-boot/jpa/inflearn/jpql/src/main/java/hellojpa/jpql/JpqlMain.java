package hellojpa.jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team1 = new Team();
            team1.setName("팀A");
            Team team2 = new Team();
            team2.setName("팀B");
            Team team3 = new Team();
            team3.setName("팀C");

            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);
            member1.setAge(100);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(team2);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원1");
            member3.setTeam(team3);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            member4.setTeam(team1);
            em.persist(member4);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate();

            System.out.println("resultCount = " + resultCount);

            System.out.println("member1.getAge() = " + member1.getAge()); // 영속성 컨텍스트에 반영이 되지않아 100살로 나옴

            em.clear(); // 또는 em.detach(member1) 영속성 컨텍스트를 초기화

            System.out.println("=============================");

            Member member = em.find(Member.class, member1.getId());
            System.out.println("after clear = " + member.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}