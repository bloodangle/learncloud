
package nthu.learncloud.dto;

import nthu.learncloud.domain.Lesson;
import nthu.learncloud.util.CustomBeanUtils;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

public class LessonDto {

    @NotBlank
    private String lessonname;
    @NotBlank
    private String sort; //課程類別
    @NotBlank
    private String teacher;

    public LessonDto() {
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
