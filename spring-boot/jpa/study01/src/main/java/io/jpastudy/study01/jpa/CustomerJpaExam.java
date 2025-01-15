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
            Customer customer = new Customer("ID0004", "Lee"); // 비영속 상태
            em.persist(customer); // 영속 상태

            // em.flush();
            Query query = em.createQuery("select c from Customer c", Customer.class);
            List<Customer> resultList = query.getResultList();
            System.out.println(resultList);

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
