package nthu.learncloud.controll;

import nthu.learncloud.domain.User;
import nthu.learncloud.form.UserForm;
import nthu.learncloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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




    @GetMapping("/registerpage")
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
        userService.save(user);
        return "redirect:/login";

    }


    @GetMapping("/login")
    public String login()
    {
        return "member/login";
    }


    /*@PostMapping("/saveline")
    public String savelin(@RequestParam String username, @RequestParam String password)
    {

        User user = userRepository.findByUsernameAndPassword(username, password);
        if( user == null) {
            return "請先註冊";
        }else
            user =userRepository.saveUsernameAndLineid(username,lineid);
            return "已儲存";
    }*/


    @PostMapping("/dologin")
    public String loginPost(@RequestParam String username, @RequestParam String password,HttpSession session){
        User user = userService.findByUsernameAndPassword(username, password);
        if( user !=null) {
            session.setAttribute("user", user);
            System.out.print(session.getId() + "\n");
            System.out.print(session.getAttribute("user"));
            return "consolo";
        }
        return "redirect:/login";
    }
    /*
    @RequestMapping("/set")
    @ResponseBody
    public String set(HttpServletRequest req)    {
        HttpSession session=req.getSession();
        session.setAttribute("testKey", "testValue");
        return "設置 session:testKey=testValue, SessionId:"+session.getId();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(HttpServletRequest req) {
        HttpSession session=req.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("SessionId", session.getId());
        map.put("testKey", session.getAttribute("testKey"));
        return map;
    }
   */




    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "index";
    }


    //Line連接確認
    @GetMapping("/linecheck")
    public String linecheck()
    {
        return "member/linetouser";
    }
}

