package nthu.learncloud.service;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import nthu.learncloud.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

    //找課程名稱
    List<Lesson> findByLessonname(String lessonname);
    //查詢id
    Lesson getlessonByid(Long id);
    //找全部
    List<Lesson> findAllLesson();


    //新增
    Lesson save(Lesson lesson);
    //更新
    Lesson update(Lesson lesson);
    //刪除
    void delete(Long id);

    //分頁查詢
    Page<Lesson> findAllByPage(Pageable pageable);


}
