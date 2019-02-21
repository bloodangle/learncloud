package nthu.learncloud;

// 關閉資料庫類別偵測
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//
@SpringBootApplication
public class LearncloudApplication  {

    public static void main(String[] args) {
        SpringApplication.run(LearncloudApplication.class, args);

    }
}



