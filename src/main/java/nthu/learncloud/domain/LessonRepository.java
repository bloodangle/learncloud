package nthu.learncloud.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    //以課程編號搜尋



    Lesson findById(long id);

    //以課程名稱搜尋
    List<Lesson> findByLessonname(String lessonname);
    //以課程名稱模糊查詢
    List<Lesson> findByLessonnameContaining(String lessonname);

    //以課程類別搜尋
    List<Lesson> findBySort(String sort);

    //以講課老師搜尋
    List<Lesson> findByTeacher(String teacher);

    //以教學單位搜尋模糊查詢
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


}



