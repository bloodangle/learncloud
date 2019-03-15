package nthu.learncloud.domain;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

    User findByEmailAndPassword(String email,String password);

    User findByUsername(String username);

    //User saveUsernameAndLineid(String username,String lineid);

    User findByEmail(String email);
}