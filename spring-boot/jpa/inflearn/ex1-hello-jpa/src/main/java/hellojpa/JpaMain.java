package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member  = new Member();
            member.setUsername("member1");

            member.setTeam(team);
            team.addMember(member);
//            team.getMembers().add(member);
            em.persist(member);

            // flush하고 clear해서 사용하지 않을거라면 양쪽 참조하는 데이터 모두 값을 셋팅해주어야 한다.
            // Member에 Team도 셋팅하고 Team에 List<Member>에도 member를 추가해 주어야 한다.
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
