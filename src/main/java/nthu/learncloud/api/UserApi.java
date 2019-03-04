package nthu.learncloud.api;

import nthu.learncloud.domain.User;
import nthu.learncloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

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
