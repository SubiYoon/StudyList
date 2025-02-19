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

            String query1 = "select m from Member m join fetch m.team";
            List<Member> result1 = em.createQuery(query1, Member.class).getResultList();

            System.out.println("result = " + result1);

            // distinct를 사용하면 결과 3개, 사용안하면 결과가 4개 - SQL쿼리의 distinct랑 같다고 생각하면 안됨
            // Team에 대한 중복값을 DB에서가 아닌 애플리케이션에서 JPA가 중복값을 제거해줌
            // 1 : N 조인시 Data가 뻥튀기 되는 현상이 있기 때문
            String query2 = "select t from Team t join fetch t.members";
            List<Team> result2 = em.createQuery(query2, Team.class).getResultList();

            for (Team team : result2) {
                System.out.println(team);
            }

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