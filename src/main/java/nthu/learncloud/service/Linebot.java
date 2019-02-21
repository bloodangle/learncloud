package nthu.learncloud.service;


import nthu.learncloud.domain.Lesson;
import nthu.learncloud.domain.LessonRepository;
import nthu.learncloud.exception.LessonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.w3c.dom.Text;

import java.util.List;

@SpringBootApplication
@LineMessageHandler
public class Linebot {
    public static void main(String[] args) {
        SpringApplication.run(Linebot.class, args);
    }

    @Autowired
    private LessonServicelmpl lessonServicelmpl;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        //System.out.print("event: " + event);
        Long X = Long.parseLong(event.getMessage().getText());
        //Lesson lesson = lessonServicelmpl.getlessonByid(X);
        Lesson lesson = lessonServicelmpl.findOne(X);
        if(lesson != null) {
            return new TextMessage("課程" + X + ":" + lesson.getLessonname() + "\n開始時間：" + lesson.getOpendate() + "\n結束時間：" + lesson.getClosedate() + "\n修課連結：" + "\nhttps://ctldtest2.tk/lesson/list=" + X);
        }else
            return new TextMessage("無此課程");
            //return new TextMessage("無法辨識" + event.getMessage().getText()
    }
    //return new TextMessage("Hello,"+event.getMessage().getText());



    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.print("event: " + event);
    }
}
