package nthu.learncloud.controll;

import com.google.common.collect.Maps;
import nthu.learncloud.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Map;


//@RestController // 只會反回內容 不會返回頁面
@Controller
public class MainController {

    @GetMapping("/")
    public String index()
    {
        return "index2";
    }
    /*
    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("測試異常");
    }*/

    @GetMapping("/index2")
    public String index2()
    {
        return "index";
    }

    @GetMapping("/admin_management")
    public ResponseEntity<?> management(HttpSession session) {
        session.getAttribute("user");
        Map<String, String> map = Maps.newHashMap();
        URI uri = URI.create("https://ctldtest2.tk/");

        if (session.getAttribute("user") != null) {
            //System.out.print(session.getAttribute("user"));
            User user = new User();
            BeanUtils.copyProperties(session.getAttribute("user"), user);
            //System.out.print(user);
            //System.out.print(user.getPermission());
            if (user.getPermission() == 4) {
                return ResponseEntity.ok("");
            } else {
                return ResponseEntity.created(uri).body("權限不足");
            }
        } else
            return ResponseEntity.ok("請先登入");
    }


    @GetMapping("/admin_management3")
    @ResponseBody
    Map<String, String> management3(HttpSession session) {
        session.getAttribute("user");
        Map<String, String> map = Maps.newHashMap();

        if(session.getAttribute("user")!=null) {
            //System.out.print(session.getAttribute("user"));
            User user = new User();
            BeanUtils.copyProperties(session.getAttribute("user"),user);
            //System.out.print(user);
            //System.out.print(user.getPermission());
            if(user.getPermission()==4) {
                map.put("message","");
            }else
            {
                map.put("message","權限不足");
            }
        }else
            map.put("message","請先登入");
            return map;
    }

    /*
    @GetMapping("/admin_management2")
    public String management2(HttpSession session){
        session.getAttribute("user");
        //System.out.print(session);
        if(session.getAttribute("user")!=null) {

            //System.out.print(session.getAttribute("user"));
            User user = new User();
            BeanUtils.copyProperties(session.getAttribute("user"),user);
            //System.out.print(user);
            //System.out.print(user.getPermission());
            if(user.getPermission()==4) {
                return "page/adminmanage";
            }else
            {
                return "index";
            }

        }else
        return "member/login";
    }*/

    @GetMapping("/re")
    public String testre(){return "member/register2";}


    //課程列表
    /*@GetMapping("/lessonlist")
    public String lessonlist()
    {
        return "page/lessonlist";
    }*/

    @GetMapping("lessonopen2")
    public String lessonopen()
    {
        return "page/lessonopen2";
    }


    @GetMapping("/lessonlist")
    public String lessonlist(HttpSession session){
        session.getAttribute("user");
        //System.out.print(session);
        if(session.getAttribute("user")!=null) {

            //System.out.print(session.getAttribute("user"));
            User user = new User();
            BeanUtils.copyProperties(session.getAttribute("user"),user);
            //System.out.print(user);
            //System.out.print(user.getPermission());
            if(user.getPermission()==4) {
                return "page/lessonlistadmin";
            }else
            {
                return "page/lessonlist";
            }

        }else
            return "page/lessonlist";
    }

    @GetMapping("/lessonlist2")
    public String lessonlist2(){
        return "page/lessonlist2";
    }
}

