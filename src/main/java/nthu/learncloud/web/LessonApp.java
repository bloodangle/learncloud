package nthu.learncloud.web;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.service.LessonServicelmpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/result2")
public class LessonApp {

    @Autowired
    private LessonServicelmpl lessonServicelmpl;




    //@GetMapping("/list")
    /*public List<Lesson> getAll()
    {
        return lessonService.findAll();
    }*/

    @GetMapping("/list2")
    public Page<Lesson> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size)
    {
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        return lessonServicelmpl.findAllByPage(PageRequest.of(page,size,sort));
    }

    @GetMapping("/{id}")
    public Lesson getOne(@PathVariable long id) {
        return lessonServicelmpl.getlessonByid(id);
    }

    @PostMapping("/r3")
    public Lesson Post(Lesson lesson)
    {
        return lessonServicelmpl.save(lesson);
    }

    @PostMapping("/by")
    public List<Lesson> findByLessonname(@RequestParam String lessonname)
    {
        return lessonServicelmpl.findByLessonname(lessonname);
    }

    @DeleteMapping("lessonlist/{id}")
    public void deleteOne(@PathVariable long id)
    {
        lessonServicelmpl.delete(id);
    }
}
