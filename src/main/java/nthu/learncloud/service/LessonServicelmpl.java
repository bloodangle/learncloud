package nthu.learncloud.service;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.domain.LessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class LessonServicelmpl implements LessonService{

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Lesson> findByLessonname(String lessonname) {
        return lessonRepository.findByLessonname(lessonname);
    }

    @Override
    public Lesson getlessonByid(Long id) {
        /*Lesson lesson = lessonRepository.findById(id).orElse(null);
        if(lesson == null)
        {
            throw new NotFoundException("課程不存在");
        }
        return lesson;*/
        return lessonRepository.findById(id).orElse(null);
    }

    public Lesson findOne(long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Lesson> findAllLesson() {
        return lessonRepository.findAll();
    }
    /////////////////////

    @Override
    public Lesson save(Lesson lesson)
    {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

    /////////////////////

    @Override
    public Page<Lesson> findAllByPage(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }




}
