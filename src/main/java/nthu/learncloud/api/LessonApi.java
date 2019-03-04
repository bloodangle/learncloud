package nthu.learncloud.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.model.response.BotApiResponse;
import nthu.learncloud.domain.Lesson;
import nthu.learncloud.dto.LessonDto;
import nthu.learncloud.exception.InvaildRequestException;
import nthu.learncloud.exception.NotFoundException;
import nthu.learncloud.service.LessonService;


import nthu.learncloud.util.CustomBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


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
