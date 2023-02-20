package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addOrEditUser(User user);
    User getUserById(Long id);
    List<User> getUserList();
    void deleteUserById(Long id);

}
