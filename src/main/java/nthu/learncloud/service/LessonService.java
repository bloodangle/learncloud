package nthu.learncloud.service;

import nthu.learncloud.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

    Lesson getlessonByid(Long id);

    void delete(Long id);

    List<Lesson> findByLessonname(String lessonname);

    //查詢全部
    Lesson save(Lesson lesson);

    //分頁查詢
    Page<Lesson> findAllByPage(Pageable pageable);

}
