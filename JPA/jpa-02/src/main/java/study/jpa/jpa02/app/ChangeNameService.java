package study.jpa.jpa02.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import study.jpa.jpa02.domain.User;
import study.jpa.jpa02.jpa.EMF;

public class ChangeNameService {
    public void changeName(String email, String newName) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            user.changeName(newName);
            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}