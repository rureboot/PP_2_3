package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addOrEditUser(User user);
    User getUserById(Long id);
    List<User> getUserList();
    void  deleteUserById(Long id);
}
