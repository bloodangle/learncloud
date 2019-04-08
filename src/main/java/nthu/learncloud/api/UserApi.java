package nthu.learncloud.api;

import nthu.learncloud.domain.User;

import nthu.learncloud.dto.AjaxResponseBody;
import nthu.learncloud.form.UserForm;
import nthu.learncloud.form.Userinfo;
import nthu.learncloud.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/result")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/userlist")
    public List<User> getAll()
    {
       return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    //@SuppressWarnings("unchecked")
    @PostMapping("/rtajax")
    public ResponseEntity<?> registerajax(@Valid @RequestBody UserForm userForm,BindingResult result)
    {
        /*if(userService.findByUsername(userForm.getUsername())!=null)
        {
            message.setMessage("用戶名已被註冊");
            return ResponseEntity.ok(message.getMessage());
        }*/
        if(userService.findByEmail(userForm.getEmail())!=null)
        {
            AjaxResponseBody message = new AjaxResponseBody();
            message.setField("email");
            message.setDefaultMessage("信箱已被註冊");
            Object []obj=new Object[1];
            obj[0]=message;

            return ResponseEntity.ok(obj);
        }

        if(!userForm.repassword())
        {
            AjaxResponseBody message = new AjaxResponseBody();
            message.setField("repassword");
            message.setDefaultMessage("密碼不一致");
            Object []obj=new Object[1];
            obj[0]=message;
            return ResponseEntity.ok(obj);
        }

        if(result.hasErrors())
        {
            //var x="";
            List<FieldError> fieldErrors= result.getFieldErrors();
            //for(FieldError error : fieldErrors)
            //{
            //    x += error.getField() + ":" + error.getDefaultMessage();
            //}
            return ResponseEntity.ok(fieldErrors);
        }
        //message.setMessage("已完成註冊");
        User user = userForm.convertToUser();
        userService.save(user);
        return ResponseEntity.ok("註冊完成");
    }

    //登入
    @PostMapping("/lgajax")
    public  ResponseEntity<?>loginajax(@Valid @RequestBody User user,HttpSession session){
        System.out.print(user.getEmail());
        System.out.print(user.getPassword());

        if(userService.findByEmail(user.getEmail())!=null )
        {
            //System.out.print(userService.findByEmailAndPassword(user.getEmail(),user.getPassword()));
            if(userService.findByEmailAndPassword(user.getEmail(),user.getPassword())!=null) {
                User user1 = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
                Userinfo userinfo= new Userinfo();
                BeanUtils.copyProperties(user1,userinfo);
                //System.out.print(userinfo);

                session.setAttribute("user",userinfo);
                return ResponseEntity.ok("ok");
            }else
            {
                return ResponseEntity.ok("Validation failed");
            }
        }else
        return ResponseEntity.ok("notfound");
    }
    //登出
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session)
    {
        session.removeAttribute("user");
        return ResponseEntity.ok("logoutfinish");
    }



    @PostMapping("/r2")
    public User Post(User user)
    {
        //User user =new User();
        //user.setUsername(username);
        //user.setEmail(Email);
        //user.setPassword(password);
        //user.setPhone(phone);
        return userService.save(user);
    }


    /*更新
    @PutMapping("/Users")
    public User update(@RequestParam long id,
                       @RequestParam String username,
                       @RequestParam String Email,
                       @RequestParam String password,
                       @RequestParam String phone)
    {
        User user =new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(Email);
        user.setPassword(password);
        user.setPhone(phone);

        return userService.save(user);
    }
    */

    /*
    @PostMapping("/by")
    public List<User> findByUsername(@RequestParam String username)
    {
        return userService.findByUsername(username);
    }
    */

    @PostMapping("/by={username}")
    public User findByUsername(@PathVariable String username)
    {
        return userService.findByUsername(username);
    }

    @PostMapping("/find={email}")
    public User findByEmail(@PathVariable String email)
    {
        return userService.findByEmail(email);
    }

}
