
package nthu.learncloud.dto;

import nthu.learncloud.domain.Lesson;
import nthu.learncloud.util.CustomBeanUtils;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LessonDto {

    private static final String SORT_REG=("^(csie)|(iot)|(ai)|(doq)|(ft)|(neu)|(bs)|(ge)|(other)$");

    private static final String CERTIFY_REG=("[0-1]{1}");

    private String lessonimg;  //課程縮圖
    private String introvideo; //課程導引影片

    @NotBlank(message = "不能為空")
    private String teacher;     //姓名
    @NotBlank(message = "不能為空")
    private String teacherimg;  //照片
    @NotBlank(message = "不能為空")
    private String unit;        //教學單位

    @NotBlank(message = "不能為空")
    private String teachereducation; //學歷
    @NotBlank(message = "不能為空")
    private String teacherexpertise; //專長

    @NotBlank(message = "不能為空")
    private String tag;

    @NotBlank(message = "不能為空")
    private String lessonname; //課程名稱


    @NotBlank(message = "不能為空")
    private String prerequisites; //先決條件
    @NotBlank(message = "不能為空")
    private String goal;   //學習目標
    @NotBlank(message = "不能為空")
    private String descrption; //描述


    @NotBlank(message = "不能為空")
    private String opendate; //開始日期
    @NotBlank(message = "不能為空")
    private String closedate; //結束日期


    @NotBlank(message = "不能為空")
    private String week; //教學進度

    @Pattern(regexp=SORT_REG,message="請選擇正確類別")
    private String sort; //課程類別
    @NotNull(message="請選擇狀態")
    private Long status;
    @NotNull(message="請選擇是否有證明")
    private Long certify; //修課證明

    private String openclassdate; //開此課程時間


    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherimg() {
        return teacherimg;
    }

    public void setTeacherimg(String teacherimg) {
        this.teacherimg = teacherimg;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLessonimg() {
        return lessonimg;
    }

    public void setLessonimg(String lessonimg) {
        this.lessonimg = lessonimg;
    }

    public String getIntrovideo() {
        return introvideo;
    }

    public void setIntrovideo(String introvideo) {
        this.introvideo = introvideo;
    }

    public String getTeachereducation() {
        return teachereducation;
    }

    public void setTeachereducation(String teachereducation) {
        this.teachereducation = teachereducation;
    }

    public String getTeacherexpertise() {
        return teacherexpertise;
    }

    public void setTeacherexpertise(String teacherexpertise) {
        this.teacherexpertise = teacherexpertise;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCertify() {
        return certify;
    }

    public void setCertify(Long certify) {
        this.certify = certify;
    }

    public String getOpenclassdate() {
        return openclassdate;
    }

    public void setOpenclassdate(String openclassdate) {
        this.openclassdate = openclassdate;
    }

    //轉換lesson
    public void convertToLesson(Lesson lesson){
        new LessonConvert().convert(this,lesson);
    }


    public Lesson convertToLesson(){
        return new LessonConvert().convert(this);
    }


    private class LessonConvert implements Convert <LessonDto, Lesson>
    {

        @Override
        public Lesson convert(LessonDto lessonDto, Lesson lesson) {
            String[] nullPropertyName = CustomBeanUtils.getNullPropertNames(lessonDto);
            BeanUtils.copyProperties(lessonDto,lesson,nullPropertyName);
            return lesson;
        }

        @Override
        public Lesson convert(LessonDto lessonDto) {
            Lesson lesson = new Lesson();
            BeanUtils.copyProperties(lessonDto,lesson);
            return lesson;
        }
    }
}
