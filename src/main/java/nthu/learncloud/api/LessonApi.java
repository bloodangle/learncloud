package nthu.learncloud.api;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.dto.LessonDto;
import nthu.learncloud.exception.InvaildRequestException;
import nthu.learncloud.exception.NotFoundException;
import nthu.learncloud.form.LessonForm;
import nthu.learncloud.service.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LessonApi {


    @Autowired
    private LessonService lessonService;

    //找全部課程
    @PostMapping("/list")
    public ResponseEntity<?> listAllLesson() {
        List<Lesson> lessons = lessonService.findAllLesson();
        if(lessons.isEmpty())
        {
            return ResponseEntity.ok("查無任何課程");
            //throw new NotFoundException("課程列表不存在");
        }
        return new ResponseEntity<List<Lesson>>(lessons, HttpStatus.OK);
    }
    /*
    @GetMapping("/list2")
    public Page<Lesson> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size)
    {
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        return lessonService.findAllByPage(PageRequest.of(page,size,sort));
    }
    */

    //@GetMapping("/list")
    /*public List<Lesson> getAll()
    {
        return lessonService.findAll();
    }*/

    //以課程編號搜尋
    @PostMapping("/lesson={id}")
    public ResponseEntity<?> lid(@PathVariable long id) {
        Lesson lesson = lessonService.findById(id);
        if (lesson == null) {
            return ResponseEntity.ok("無此課程");
        }
        return new ResponseEntity<Object>(lesson, HttpStatus.OK);
    }

    //以課程名稱搜尋  // lesson_name?lessonname=Arduino基礎
    @PostMapping("/lesson_name")
    public ResponseEntity<?> lname(@RequestParam String lessonname) {
        List<Lesson> lessons = lessonService.findByLessonnameContaining(lessonname);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("無此課程");
        }
        return new ResponseEntity<Object>(lessons, HttpStatus.OK);
    }


    //以課程類別搜尋
    @PostMapping("/lessonsort={sort}")
    public ResponseEntity<?> lsort(@PathVariable String sort) {
        List<Lesson> lessons = lessonService.findBySort(sort);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("無此類別");
        }
        return new ResponseEntity<Object>(lessons, HttpStatus.OK);
    }



    //以講課老師搜尋  // lesson_teacher?teacher=莊老師
    @PostMapping("/lesson_teacher")
    public ResponseEntity<?> ltecher(@RequestParam String teacher) {
        List<Lesson> lessons = lessonService.findByTeacher(teacher);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("查無此講師");
        }
        return new ResponseEntity<Object>(lessons, HttpStatus.OK);
    }

    //以教學單位模糊搜尋
    @PostMapping("/lesson_unit")
    public ResponseEntity<?> lu(@RequestParam String unit){
        List<Lesson> lessons = lessonService.findByUnitContaining(unit);
        if(lessons.isEmpty())
        {
            return ResponseEntity.ok("查無相關單位");
        }
        return new ResponseEntity<Object>(lessons,HttpStatus.OK);
    }

    //以講課老師以及課程名稱搜尋
    @PostMapping("/lesson_teaname")
    public ResponseEntity<?> lten(@RequestParam String teacher, @RequestParam String lessonname) {
        List<Lesson> lessons = lessonService.findByTeacherAndLessonnameContaining(teacher, lessonname);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("查無匹配");
        }
        return new ResponseEntity<Object>(lessons, HttpStatus.OK);
    }

    //以課程狀態搜尋 https://ctldtest2.tk/api/lesson_status?status=0
    @PostMapping("/lesson_status")
    public ResponseEntity<?> lst(@RequestParam Long status) {
        List<Lesson> lessons = lessonService.findByStatus(status);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("查無匹配");
        }
        return new ResponseEntity<Object>(lessons, HttpStatus.OK);
    }

    //以修課證明搜尋 https://ctldtest2.tk/api/lesson_certify?certify=0
    @PostMapping("/lesson_certify")
    public ResponseEntity<?> lct(@RequestParam Long certify)
    {
        List<Lesson> lessons =lessonService.findByCertify(certify);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("查無匹配");
        }
        return new ResponseEntity<Object>(lessons,HttpStatus.OK);
    }

    //以開課日期搜尋
    @PostMapping("/lesson_date")
    public ResponseEntity<?> ldate(@RequestParam String opendate)
    {
        List<Lesson> lessons =lessonService.findByOpendate(opendate);
        if (lessons.isEmpty()) {
            return ResponseEntity.ok("查無匹配");
        }
        return new ResponseEntity<Object>(lessons,HttpStatus.OK);
    }


    /*
    @PostMapping("/post")
    //@RequestBody 傳送json
    public ResponseEntity<?> Post(@Valid @RequestBody LessonDto lessonDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok("請求無效"+bindingResult);
            //throw new InvaildRequestException("請求無效", bindingResult);
        }
        Lesson lesson1 = lessonService.save(lessonDto.convertToLesson());
        return new ResponseEntity<Object>(lesson1, HttpStatus.CREATED);
    }*/

    //

    @PostMapping("/lessonopen")
    public ResponseEntity<?> openajax(@Valid @RequestBody LessonForm lessonForm,BindingResult result)
    {
        if(result.hasErrors())
        {
            List<FieldError> fieldErrors = result.getFieldErrors();
            return ResponseEntity.ok(fieldErrors);
        }
        Lesson lesson = lessonForm.convertToLesson();
        lessonService.save(lesson);
        return ResponseEntity.ok("已新增成功");
    }


    @PutMapping("/update={id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody LessonDto lessonDto, BindingResult bindingResult) {
        Lesson current = lessonService.findById(id);
        if (current == null) {
            return new ResponseEntity<Object>("此課程未找到", HttpStatus.OK);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>("請求有誤", HttpStatus.OK);
        }
        lessonDto.convertToLesson(current);
        lessonService.update(current);
        return new ResponseEntity<Object>("已修改"+current.getLessonname()+"完成", HttpStatus.OK);
    }


    @DeleteMapping("/delete={id}")
    public ResponseEntity<?> deleteOne(@PathVariable long id) {
        lessonService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /*
    @PostMapping("/upload")
    public String upftpfile() {







        return "haha";
    }*/
}
