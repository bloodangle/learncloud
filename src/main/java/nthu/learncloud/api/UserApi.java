package nthu.learncloud.api;

import nthu.learncloud.domain.User;
import nthu.learncloud.domain.UserRepository;
import nthu.learncloud.form.UserForm;
import nthu.learncloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/result")
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userlist")
    public List<User> getAll()
    {
       return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PostMapping("/rt")
    public ResponseEntity<?> registerPost(@Valid @RequestBody UserForm userForm, BindingResult result)
    {
        if(!userForm.repassword()) {
            return ResponseEntity.badRequest().body("密碼不一致");
        }
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(result);
        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return ResponseEntity.ok(result);
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

    @PostMapping("/by")
    public List<User> findByUsername(@RequestParam String username)
    {
        return userService.findByUsername(username);
    }

}
