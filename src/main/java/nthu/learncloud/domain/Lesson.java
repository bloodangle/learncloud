package nthu.learncloud.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //自動生成

    private Long id;
    private String lessonname;
    private String sort; //課程類別
    private String teacher;
    private String opendate; //開始日期
    private String closedate; //結束日期
    private int status; //狀態  0尚未開課 1開課中 2已開課完畢
    private String intro; // 介紹
    private String intro2; //詳情介紹


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getClosedate() {
        return closedate;
    }

    public void setClosedate(String closedate) {
        this.closedate = closedate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro2() {
        return intro2;
    }

    public void setIntro2(String intro2) {
        this.intro2 = intro2;
    }
}