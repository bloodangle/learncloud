package nthu.learncloud.api;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.dto.LessonDto;
import nthu.learncloud.exception.InvaildRequestException;
import nthu.learncloud.exception.NotFoundException;
import nthu.learncloud.service.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LessonApi {


    @Autowired
    private LessonService lessonService;

    @GetMapping("/list")
    public ResponseEntity<?> listAllLessons()
    {
        List<Lesson> lessons = lessonService.findAllLesson();
        if(lessons.isEmpty())
        {
            throw new NotFoundException("課程列表不存在");
        }
        return new ResponseEntity<List<Lesson>>(lessons,HttpStatus.OK);
    }

    @GetMapping("/list2")
    public Page<Lesson> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size)
    {
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        return lessonService.findAllByPage(PageRequest.of(page,size,sort));
    }

    //@GetMapping("/list")
    /*public List<Lesson> getAll()
    {
        return lessonService.findAll();
    }*/

    @PostMapping("/lesson={id}")
    public ResponseEntity<?> getLessong(@PathVariable Long id)
    {
        Lesson lesson = lessonService.getlessonByid(id);
        if(lesson == null)
        {
            throw new NotFoundException(String.format("此%s課程未找到",id));
        }
        return new ResponseEntity<Object>(lesson, HttpStatus.OK);
    }
    /*
    @PostMapping("/lesson={lessname}")
    public ResponseEntity<?> getLessonname(@PathVariable String lessonname)
    {
        Lesson lesson = lessonService.findByLessonname(lessonname);
        if(lesson == null)
        {

        }
        return new ResponseEntity<Object>(lesson,HttpStatus.OK)
    }*/



    /*@GetMapping("/2/{id}")
    public Lesson getOne(@PathVariable Long id) {
        return lessonService.getlessonByid(id);
    }*/

    @PostMapping("/post")
    //@RequestBody 傳送json
    public ResponseEntity<?> Post(@Valid @RequestBody LessonDto lessonDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            throw new InvaildRequestException("請求無效",bindingResult);
        }
        Lesson lesson1 = lessonService.save(lessonDto.convertToLesson());
        return new ResponseEntity<Object>(lesson1,HttpStatus.CREATED);
    }
    /*
    @PostMapping("/by")
    public List<Lesson> findByLessonname(@RequestParam String lessonname)
    {
        return lessonService.findByLessonname(lessonname);
    }
    */


    @PutMapping("/update={id}")
    public ResponseEntity<?> update (@PathVariable Long id,@Valid @RequestBody LessonDto lessonDto,BindingResult bindingResult)
    {
        Lesson current= lessonService.getlessonByid(id);
        if(current == null)
        {
            throw new NotFoundException(String.format("此%s課程未找到",id));
        }
        if(bindingResult.hasErrors()){
            throw new InvaildRequestException("請求有誤",bindingResult);
        }
        lessonDto.convertToLesson(current);
        Lesson lesson1 =lessonService.update(current);
        return new ResponseEntity<Object>(lesson1,HttpStatus.OK);
    }



    @DeleteMapping("/delete={id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id)
    {
        lessonService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
