package hellojpa;

import net.bytebuddy.matcher.DefinedShapeMatcher;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "1000"));

            //
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("보쌈");

            member.getAddressHistory().add(new Address("old1", "street", "10"));
            member.getAddressHistory().add(new Address("old2", "street", "100"));

            em.persist(member);

            em.flush();
            em.clear();

            // homeCity -> newCity
            Member findMember = em.find(Member.class, member.getId());
            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //
            findMember.getAddressHistory().remove(new Address("old1", "street", "10"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

            em.flush();
            em.clear();

           Member findMember2 = em.find(Member.class, member.getId());

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
