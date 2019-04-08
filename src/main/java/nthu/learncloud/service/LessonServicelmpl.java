package nthu.learncloud.service;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.domain.LessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class LessonServicelmpl implements LessonService{

    @Autowired
    private LessonRepository lessonRepository;

    //以課程編號搜尋
    @Override
    public Lesson findById(long id) {
        /*Lesson lesson = lessonRepository.findById(id).orElse(null);
        if(lesson == null)
        {
            throw new NotFoundException("課程不存在");
        }
        return lesson;*/
        return lessonRepository.findById(id);
    }


    //以課程名稱搜尋
    @Override
    public List<Lesson> findByLessonname(String lessonname) {
        return lessonRepository.findByLessonname(lessonname);
    }

    @Override
    public List<Lesson> findByLessonnameContaining(String lessonname){
        return lessonRepository.findByLessonnameContaining(lessonname);
    }

    //以課程類別搜尋
    @Override
    public List<Lesson> findBySort(String sort) {return lessonRepository.findBySort(sort);}

    //以講課老師搜尋
    @Override
    public List<Lesson> findByTeacher(String teacher) {return lessonRepository.findByTeacher(teacher);}

    //以教學單位模糊搜尋
    @Override
    public List<Lesson> findByUnitContaining(String unit) {return lessonRepository.findByUnitContaining(unit);}

    //以講課老師以及課程名稱搜尋
    @Override
    public List<Lesson> findByTeacherAndLessonname(String teacher,String lessonname) {
        return lessonRepository.findByTeacherAndLessonname(teacher,lessonname);
    }

    @Override
    public List<Lesson> findByTeacherAndLessonnameContaining(String teacher,String lessonname) {
        return lessonRepository.findByTeacherAndLessonnameContaining(teacher,lessonname);
    }



    //以課程狀態搜尋
    @Override
    public List<Lesson> findByStatus(Long status) {
        return lessonRepository.findByStatus(status);
    }

    //以修課證明搜尋
    @Override
    public List<Lesson> findByCertify(Long certify)
    {
        return lessonRepository.findByCertify(certify);
    }
    //以修課證明和老師搜尋
    @Override
    public List<Lesson> findByTeacherAndCertify(String teacher,Long certify)
    {
        return lessonRepository.findByTeacherAndCertify(teacher,certify);
    }

    //以開課日期搜尋
    @Override
    public List<Lesson> findByOpendate(String opendate)
    {
        return lessonRepository.findByOpendate(opendate);
    }

    //尋找全部頁面
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
    public void delete(long id) {
        lessonRepository.deleteById(id);
    }

}
