package nthu.learncloud.form;

import nthu.learncloud.domain.User;
import nthu.learncloud.domain.UserRepository;
import nthu.learncloud.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {


    //public static final String PHONE_REG = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
    public static final String PHONE_REG = ("09[0-9]{2}[0-9]{6}");

    @NotBlank(message = "不能為空")
    private String username;

    @NotBlank(message = "不能為空")
    @Email(message = "請填入正確格式")
    private String email;

    @Pattern(regexp = PHONE_REG,message = "需要正確的手機號")
    private String phone;

    @NotBlank(message = "不能為空,密碼至少6位")
    @Length(min = 6,message = "密碼至少6位")
    private String password;

    @NotBlank(message = "不能為空")
    private String repassword;

    private String permission;


    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    //
    public boolean repassword() {


        if(this.password.equals(this.repassword))
        {
            return true;
        }
        return false;
    }


    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public User convertToUser()
    {
        User user = new UserFormConvert().convert(this);
        return user;
    }

    private class UserFormConvert implements FormConvert<UserForm, User>{

        @Override
        public User convert(UserForm userForm){
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }
}
