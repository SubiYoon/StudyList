package study.jpa.jpa02.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import study.jpa.jpa02.domain.User;
import study.jpa.jpa02.jpa.EMF;

public class NewUserService {

    public void saveNewUser(User user) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
