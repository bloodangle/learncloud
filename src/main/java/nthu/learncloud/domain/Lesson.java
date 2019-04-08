package nthu.learncloud.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="lesson2")
@Entity
@Data
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //自動生成

    private long id;  //課程編號
    private String lessonimg; //課程縮圖
    private String introvideo; //課程導引影片

    //講師
    private String teacher;     //姓名
    private String teacherimg;  //照片
    private String unit;        //教學單位
    private String teachereducation; //學歷
    private String teacherexpertise; //專長

    private String tag; //課程tag

    private String lessonname; //課程名稱
    private String sort; //課程類別

    @Column(columnDefinition = "TEXT")
    private String prerequisites; //先決條件
    @Column(columnDefinition = "TEXT")
    private String goal;   //學習目標
    @Column(columnDefinition = "TEXT")
    private String descrption; //描述

    private Long status; //開課設定  0尚未開課 1開課中 2已開課完畢

    private String opendate; //開始日期
    private String closedate; //結束日期

    @Column(columnDefinition = "TEXT")
    private String week; //教學進度
    private Long certify; //修課證明
    private String openclassdate; //開此課程時間

}