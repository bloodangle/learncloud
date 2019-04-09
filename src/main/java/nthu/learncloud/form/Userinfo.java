package nthu.learncloud.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class Userinfo implements Serializable{


    private Long id;
    private String username;
    private String email;
    private String status;

    private String lineid; //推播用Lineid

    private String phone;

    private int permission;



}