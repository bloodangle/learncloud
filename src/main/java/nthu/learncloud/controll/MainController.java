package nthu.learncloud.controll;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController // 只會反回內容 不會返回頁面
@Controller
public class MainController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/exception")
    public String testException()
    {
        throw new RuntimeException("測試異常");
    }


}

