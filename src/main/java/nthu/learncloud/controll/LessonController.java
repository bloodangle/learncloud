package nthu.learncloud.controll;

import nthu.learncloud.domain.Lesson;
import nthu.learncloud.dto.LessonDto;
import nthu.learncloud.exception.InvaildRequestException;
import nthu.learncloud.exception.NotFoundException;
import nthu.learncloud.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class LessonController {

    //private  final Logger logger = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    private LessonService lessonService;
    /*
    //DESC 倒序 ASC正序
    @GetMapping("/lessonlist")
    public String lessonlist(@PageableDefault(size = 5,sort ={"id"},direction = Sort.Direction.ASC) Pageable pageable, Model model)
    {
        Page<Lesson> page1 = lessonService.findAllByPage(pageable);
        model.addAttribute("page",page1);
        return "page/lessonlist";
    }*/



    /** 課程詳情 */

    @GetMapping("/lesson/list={id}")
    public String lessondetail(@PathVariable Integer id, Model model)
    {
        Lesson lesson = lessonService.findById(id);
        model.addAttribute("lesson",lesson);
        return "page/lesson";
    }

    @GetMapping("/lesson/sort={sort}")
    public ResponseEntity<?> getSort(@PathVariable String sort)
    {
        List<Lesson> lessons =lessonService.findBySort(sort);

        return new ResponseEntity<List<Lesson>>(lessons,HttpStatus.OK);

    }


    //


    /** 新增課程 */
    @GetMapping("/lessonopen")
    public String lessonopen(Model model)
    {
        model.addAttribute("lesson",new Lesson());
        return "page/lessonopen";
    }

    @PostMapping("/lessonlist2")
    public String post(Lesson lesson, final RedirectAttributes attributes)
    {
        /*
        Lesson lesson1 = lessonService.save(lesson);
        if(lesson1 != null)
        {
            attributes.addFlashAttribute("message","《"+lesson1.getLessonname()+"》增加成功");
        }*/
        attributes.addFlashAttribute("messagedo","增加成功");
        //redirect 調用回到某頁面
        return "redirect:/lessonlist2";

        /**
                *  redirect 是兩次請求 post .get
                * Model 只能一次請求
                * 需要用 RedirectAttributes
               */
    }

    //以課程名稱模糊搜尋  // lesson_name?lessonname=Arduino基礎
    @GetMapping("/lesson_name")
    public String lname(@RequestParam String lessonname,final RedirectAttributes attributes) {
        List<Lesson> lessons = lessonService.findByLessonnameContaining(lessonname);
        if (lessons.isEmpty()) {
            attributes.addFlashAttribute("message","無此課程");
            return "redirect:/lessonlist2";
        }
        attributes.addFlashAttribute("message","/api/lesson_name?lessonname="+lessonname);
        return "redirect:/lessonlist2";
    }

    //以課程類別搜尋
    @GetMapping("/lessonsort={sort}")
    public String lsort(@PathVariable String sort,final RedirectAttributes attributes) {
        //List<Lesson> lessons = lessonService.findBySort(sort);

        attributes.addFlashAttribute("message","/api/lessonsort="+sort);
        return "redirect:/lessonlist2";
    }

    //以修課證明搜尋 https://ctldtest2.tk/api/lesson_certify?certify=0
    @GetMapping("/lesson_certify={certify}")
    public String lct(@PathVariable Long certify,final RedirectAttributes attributes)
    {
        List<Lesson> lessons =lessonService.findByCertify(certify);
        if (lessons.isEmpty()) {
            attributes.addFlashAttribute("message","查無匹配");
            return "redirect:/lessonlist2";
        }
        attributes.addFlashAttribute("message","/api/lesson_certify?certify="+certify);
        return "redirect:/lessonlist2";
    }




    @DeleteMapping("/lesson/del={id}")
    public String delete(@PathVariable long id,final RedirectAttributes attributes)
    {
        lessonService.delete(id);
        attributes.addFlashAttribute("messagedo","《"+id+"》刪除成功");
        return "redirect:/lessonlist2";
    }


    //

    /**課程修改*/
    @GetMapping("/lesson/edit={id}")
    public String edit(@PathVariable long id,Model model,final RedirectAttributes attributes)
    {
        Lesson lesson = lessonService.findById(id);
        if (lesson != null)
        {
            model.addAttribute("lesson",lesson);
            return "page/lessonopen2";
        }else
        {
            attributes.addFlashAttribute("messagedo","無權限進行此操作");
            return "page/lessonlist2";
        }
    }



    /*局部 Controller 異常處理
    public ModelAndView handleException(HttpServletRequest request,Exception e) throws Exception
    {
        logger.error("Request URL:{} , Exception:{}",request.getRequestURL(),e.getMessage());

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null)
        {
            throw e;
        }

        ModelAndView mav =new ModelAndView();
        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");

        return mav;
    }
     */


}
