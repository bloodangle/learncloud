package nthu.learncloud.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
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


}