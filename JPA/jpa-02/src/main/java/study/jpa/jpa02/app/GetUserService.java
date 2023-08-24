package study.jpa.jpa02.app;

import jakarta.persistence.EntityManager;
import study.jpa.jpa02.domain.User;
import study.jpa.jpa02.jpa.EMF;

public class GetUserService {
    public User getUser(String email) {
        EntityManager em = EMF.createEntityManager();
        try {
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            return user;
        } finally {
            em.close();
        }
    }
}
