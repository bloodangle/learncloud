package nthu.learncloud.service;

import nthu.learncloud.domain.User;
import nthu.learncloud.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //全查詢
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    //2.x版本方式
    public User findOne(long id){
        return userRepository.findById(id).orElse(null);
    }

    //創建
    public User save(User user)
    {
        return userRepository.save(user);
    }

    //根據使用者名稱查詢
    public List<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    //刷新
    /*
    @Transactional //有事務執行會全正確才會刷新 無則照做成功
    @Modifying
    @Query

    public int deleteAndUpdate()
    {
        int dcount = UserRepository.deleteByJPQL(id);
        int ucount = UserRepository.updateByJPQL(status,uid);

    }
    */
}
