package nthu.learncloud.service;


import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EnableLineMessaging;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;
import nthu.learncloud.domain.Lesson;
import nthu.learncloud.domain.LessonRepository;
import nthu.learncloud.exception.LessonNotFoundException;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;


import java.util.List;
//@SpringBootApplication

@LineMessageHandler
public class Linebot {
    /*public static void main(String[] args) {
        SpringApplication.run(Linebot.class, args);
    }*/

    @Autowired
    private LessonServicelmpl lessonServicelmpl;



    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String s1 = event.getMessage().getText();
        boolean key;
        //event.getSource().getUserid() 獲取此會員所登入的房間id
        /*if (key = s1.contains("登入"))
        {
            return new TextMessage("會員ID:" +event.getSource().getUserId() + "已登記");
        }else*/
        //System.out.print("event: " + event);

        Long X = Long.parseLong(event.getMessage().getText());
        //Lesson lesson = lessonServicelmpl.getlessonByid(X);
        Lesson lesson = lessonServicelmpl.findOne(X);
        if(lesson != null) {
            return new TextMessage(s1 +"\n課程:" + lesson.getLessonname() + "\n開始時間：" + lesson.getOpendate() + "\n結束時間：" + lesson.getClosedate() + "\n修課連結：" + "\nhttps://ctldtest2.tk/lesson/list=" + X);
        }else
            return new TextMessage("無此課程");
            //return new TextMessage("無法辨識" + event.getMessage().getText()

    //}
        //return new TextMessage(""+key);
    }


    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.print("event: " + event);
    }
}
