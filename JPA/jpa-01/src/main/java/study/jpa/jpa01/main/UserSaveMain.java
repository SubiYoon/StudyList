package study.jpa.jpa01.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import study.jpa.jpa01.User;

import java.time.LocalDateTime;

public class UserSaveMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = new User("user@user.com", "user", LocalDateTime.now());
            entityManager.persist(user); //데이터를 Inser할 때 사용한다.
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        emf.close();
    }
}
