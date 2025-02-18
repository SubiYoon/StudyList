package hellojpa.jpql;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            String query1 = em.createQuery("select m.username from Member m where m.username = :membername", String.class)
                    .setParameter("membername", "member1")
                    .getSingleResult();
            TypedQuery<Member> query2 = em.createQuery("select m from Member m", Member.class);
            List<MemberDTO> query3 = em.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            System.out.println("resultList = " + query3.get(0).toString());
            System.out.println("singleResult = " + query1);

            System.out.println("===============================================");

            Member member2 = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setMemberType(MemberType.USER);
            em.persist(member2);

            String query = "select m.username, 'Hello', true from Member m where m.memberType = hellojpa.jpql.MemberType.USER";
            List<Object[]> result = em.createQuery(query).getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
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