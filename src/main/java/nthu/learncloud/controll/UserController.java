package nthu.learncloud.controll;

import nthu.learncloud.domain.User;
import nthu.learncloud.form.UserForm;
import nthu.learncloud.domain.UserRepository;
import nthu.learncloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/userlist")
    public String userlist(Model model)
    {
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "member/userlist";
    }

    @GetMapping("/users/{id}")
    public String detail(@PathVariable long id, Model model)
    {

        User user = userService.findOne(id);
        if(user == null)
        {
            user = new User();
        }
        model.addAttribute("user",user);
        return "member/userdata";
    }
    public String a()
    {
        return "member/userlist";
    }





    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("userForm",new UserForm());
        return "member/register";
    }


    @PostMapping("/register")
    public String registerPost(@Valid UserForm userForm, BindingResult result)
    {
        if(!userForm.repassword()) {
            result.rejectValue("repassword", "confirmError", "密碼不一致");
        }
        if(result.hasErrors())
        {
            return "member/register";
        }
        /*
        boolean chick =true;
        if(!userForm.repassword())
        {
            result.rejectValue("repassword","密碼不一致","confirmError");
            chick = false;
        }
        if(result.hasErrors())
        {
            List<FieldError> fieldErrors= result.getFieldErrors();
            for(FieldError error : fieldErrors){
                System.out.println(error.getField() + ":" + error.getDefaultMessage() + ":" + error.getCode());
            }
            chick = false;
        }
        if(!chick)
        {
            return "member/register";
        }
        */
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";

    }


    @GetMapping("/login")
    public String login()
    {
        return "member/login";
    }
}

