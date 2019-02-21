package nthu.learncloud.controll;

import nthu.learncloud.domain.Lesson;
import nthu.learncloud.exception.LessonNotFoundException;
import nthu.learncloud.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class LessonController {

    //private  final Logger logger = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    private LessonService lessonService;


    //DESC 倒序 ASC正序
    @GetMapping("/lessonlist")
    public String lessonlist(@PageableDefault(size = 5,sort ={"id"},direction = Sort.Direction.ASC) Pageable pageable, Model model)
    {
        Page<Lesson> page1 = lessonService.findAllByPage(pageable);
        model.addAttribute("page",page1);
        return "page/lessonlist";
    }



    /** 課程詳情 */

    @GetMapping("/lesson/list={id}")
    public String lessodetail(@PathVariable long id, Model model)
    {
        Lesson lesson = lessonService.getlessonByid(id);
        model.addAttribute("lesson",lesson);
        return "page/lesson";
    }




    //


    /** 新增課程 */
    @GetMapping("/lessonopen")
    public String lessonopen(Model model)
    {
        model.addAttribute("lesson",new Lesson());
        return "page/lessonopen";
    }

    @PostMapping("/lessonlist")
    public String post(Lesson lesson, final RedirectAttributes attributes)
    {
        Lesson lesson1 = lessonService.save(lesson);
        if(lesson1 != null)
        {
            attributes.addFlashAttribute("message","《"+lesson1.getLessonname()+"》增加成功");
        }
        //redirect 調用回到某頁面
        return "redirect:/lessonlist";

        /**
                *  redirect 是兩次請求 post .get
                * Model 只能一次請求
                * 需要用 RedirectAttributes
               */
    }


    @GetMapping("/lesoon/del={id}")
    public String delete(@PathVariable long id,final RedirectAttributes attributes)
    {
        lessonService.delete(id);
        attributes.addFlashAttribute("message","《"+id+"》刪除成功");
        return "redirect:/lessonlist";
    }


    //

    /**課程修改*/
    @GetMapping("/lesson/edit={id}")
    public String edit(@PathVariable long id,Model model)
    {
        Lesson lesson = lessonService.getlessonByid(id);
        model.addAttribute("lesson",lesson);
        return "page/lessonopen";
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
