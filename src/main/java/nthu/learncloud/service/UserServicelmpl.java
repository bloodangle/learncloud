package nthu.learncloud.service;

import nthu.learncloud.domain.User;
import nthu.learncloud.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //全查詢
    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    //2.x版本方式
    @Override
    public User findOne(long id){
        return userRepository.findById(id).orElse(null);
    }

    //驗證使用者帳密
    public User findByUsernameAndPassword(String username,String password){return userRepository.findByUsernameAndPassword(username,password);}

    public User findByEmailAndPassword(String email,String password){return userRepository.findByEmailAndPassword(email,password);}

    //創建
    @Override
    public User save(User user)
    {
        return userRepository.save(user);
    }

    //根據使用者名稱查詢
    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail (String email)
    {
        return userRepository.findByEmail(email);
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
