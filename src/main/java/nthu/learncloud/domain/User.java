package nthu.learncloud.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //自動生成

    private Long id;
    private String username;
    private String email;
    private String status;

    private String identity;  //身分證
    private String experience;  //經歷
    private String introduction;  //簡介

    private String lineid; //推播用Lineid

    private String phone;
    private String password;

    private String pd; // 照片

    private int permission;

    private String homepage; //個人網站


}