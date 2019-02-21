package nthu.learncloud.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);
}