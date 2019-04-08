package nthu.learncloud.service;



import nthu.learncloud.domain.Lesson;
import org.springframework.beans.factory.annotation.Autowired;


import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//@SpringBootApplication

@LineMessageHandler
public class Linebot {
    /*public static void main(String[] args) {
        SpringApplication.run(Linebot.class, args);
    }*/

    @Autowired
    private LessonServicelmpl lessonServicelmpl;


    /*@EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String s1 = event.getMessage().getText();

        boolean key;
        //event.getSource().getUserid() 獲取此會員所登入的房間id
        /*if (key = s1.contains("登入"))
        {
            return new TextMessage("會員ID:" +event.getSource().getUserId() + "已登記");
        }else*/
        //System.out.print("event: " + event);

        /*
        Long X = Long.parseLong(event.getMessage().getText());
        //Lesson lesson = lessonServicelmpl.getlessonByid(X);
        Lesson lesson = lessonServicelmpl.getlessonByid(X);
        if(lesson != null) {
            return new TextMessage(s1 +"\n課程:" + lesson.getLessonname() + "\n開始時間：" + lesson.getOpendate() + "\n結束時間：" + lesson.getClosedate() + "\n修課連結：" + "\nhttps://ctldtest2.tk/lesson/list=" + X);
        }else
            return new TextMessage("無此課程");
            //return new TextMessage("無法辨識" + event.getMessage().getText()

    //}
        //return new TextMessage(""+key);
    }*/

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String s1 = event.getMessage().getText();
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(s1);
        Long X = null;

        boolean key;

        if(!isNum.matches())
        {
            if (key = s1.contains("登入"))
            {
                return new TextMessage("會員ID:" +event.getSource().getUserId() + "已登記");
            }else
                return new TextMessage("請確認功能");
        } else

            X = Long.parseLong(event.getMessage().getText());
            //Lesson lesson = lessonServicelmpl.getlessonByid(X);
            Lesson lesson = lessonServicelmpl.findById(X);
            if(lesson != null) {
                return new TextMessage(s1 +"\n課程:" + lesson.getLessonname() + "\n開始時間：" + lesson.getOpendate() + "\n結束時間：" + lesson.getClosedate() + "\n修課連結：" + "\nhttps://ctldtest2.tk/lesson/list=" + X);
        }else
            return new TextMessage("無此課程");
    }




    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.print("event: " + event);
    }
}
