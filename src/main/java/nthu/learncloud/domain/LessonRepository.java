package nthu.learncloud.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    List<Lesson> findByLessonname(String lessonname);

    //分頁查詢
    Page<Lesson> findAll(Pageable pageable);
}



