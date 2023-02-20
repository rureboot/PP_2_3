package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManagerFactory factory) {

        this.entityManager = factory.createEntityManager();
    }


    @Override
    public void addOrEditUser(User user) {

        if (user.getId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.createQuery("select u from User u where u.id=:userId", User.class)
                .setParameter("userId", id).getSingleResult();
    }

    @Override
    public List<User> getUserList() {

        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void deleteUserById(Long id) {

        User user = getUserById(id);
        entityManager.remove(user);
    }
}
