package nthu.learncloud.service;

import nthu.learncloud.domain.User;


import java.util.List;


public interface UserService {
    List<User> findAll();

    User findOne(long id);

    User save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameAndPassword(String username,String password);

    User findByEmailAndPassword(String email,String password);
}
