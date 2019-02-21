package nthu.learncloud.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import nthu.learncloud.domain.Lesson;
import nthu.learncloud.domain.LessonRepository;
import nthu.learncloud.exception.LessonNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.annotations.SortType;

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
    public Lesson getlessonByid(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if(lesson == null)
        {
            throw new LessonNotFoundException("課程不存在");
        }
        return lesson;
    }

    @Override
    public List<Lesson> findByLessonname(String lessonname) {
        return lessonRepository.findByLessonname(lessonname);
    }

    @Override
    public Page<Lesson> findAllByPage(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    public Lesson findOne(long id) {
        return lessonRepository.findById(id).orElse(null);
    }


    public Lesson save(Lesson lesson)
    {
        return lessonRepository.save(lesson);
    }

    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
