package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

//    private List<User> users;
//    private Long count = 0L;
//
//    {
//        users = new ArrayList<User>();
//        users.add(new User(++count, "Ivan", 50));
//        users.add(new User(++count, "Solod", 55));
//        users.add(new User(++count, "Alex", 23));
//    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
//        user.setId(++count);
//        users.add(user);
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
//        return users;
           return entityManager.createQuery("select u from User u", User.class).getResultList();

    }

    @Override
    public User getUserById(long id) {
//        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(long id, User user) {
        User toBeUpdate = getUserById(id);
        toBeUpdate.setUsername(user.getUsername());
        toBeUpdate.setAge(user.getAge());
    }

    @Override
    public void deleteUser(long id) {
//        users.removeIf(u -> u.getId() == id);
        entityManager.remove(getUserById(id));
    }
}
