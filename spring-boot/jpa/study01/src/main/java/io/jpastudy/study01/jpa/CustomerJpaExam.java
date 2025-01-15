package io.jpastudy.study01.jpa;

import io.jpastudy.study01.jpa.entity.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study01");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        //
        try {
            Customer customer = new Customer("ID0005", "Jin"); // 비영속 상태
            em.persist(customer); // 영속 상태
            em.detach(customer); // 준영속 상태

            // 쿼리를 날려 DB에서 가져온다.
            Customer foundCustomer = em.find(Customer.class, "ID0005");
            System.out.println(foundCustomer.toString());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
        //
    }
}
