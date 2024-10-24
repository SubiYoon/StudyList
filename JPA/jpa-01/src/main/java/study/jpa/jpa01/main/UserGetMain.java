package study.jpa.jpa01.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import study.jpa.jpa01.User;

public class UserGetMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, "user@user.com");
            if(user == null){
                System.out.println("User 없음");
            } else {
                System.out.printf("User 있음, email=%s, name=%s, createTime=%s\n", user.getEmail(), user.getName(), user.getCreateDate());
            }
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
