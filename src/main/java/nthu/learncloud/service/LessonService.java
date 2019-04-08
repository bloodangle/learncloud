package nthu.learncloud.service;

import nthu.learncloud.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

    //以課程編號搜尋
    Lesson findById(long id);

    //以課程名稱搜尋
    List<Lesson> findByLessonname(String lessonname);
    List<Lesson> findByLessonnameContaining(String lessonname);

    //以課程類別搜尋
    List<Lesson> findBySort(String sort);

    //以講課老師搜尋
    List<Lesson> findByTeacher(String teacher);


    //以教學單位模糊搜尋
    List<Lesson> findByUnitContaining(String unit);

    //以講課老師以及課程名稱搜尋
    List<Lesson> findByTeacherAndLessonname(String teacher,String lessonname);

    List<Lesson> findByTeacherAndLessonnameContaining(String teacher,String lessonname);


    //以課程狀態搜尋
    List<Lesson> findByStatus(Long status);

    //以修課證明搜尋
    List<Lesson> findByCertify(Long certify);
    //以修課證明和老師搜尋
    List<Lesson> findByTeacherAndCertify(String teacher,Long certify);

    //以開課日期搜尋
    List<Lesson> findByOpendate(String opendate);

    //找全部
    List<Lesson> findAllLesson();

    //查詢全部 - 分頁
    //Page<Lesson> findAll(Pageable pageable);


    //新增
    Lesson save(Lesson lesson);
    //更新
    Lesson update(Lesson lesson);
    //刪除
    void delete(long id);




}
