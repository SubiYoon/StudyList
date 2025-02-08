package jpabasic.ex1hellojpa;

import jpabasic.ex1hellojpa.jpashop.Order;
import jpabasic.ex1hellojpa.jpashop.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//@SpringBootApplication
public class Ex1HelloJpaApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Ex1HelloJpaApplication.class, args);
//    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
