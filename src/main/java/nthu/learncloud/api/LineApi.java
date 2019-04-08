package nthu.learncloud.api;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import nthu.learncloud.domain.Lesson;
import nthu.learncloud.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/lineapi")
public class LineApi {

    @Autowired
    private LessonService lessonService;
    @Autowired
    private LineMessagingClient lineMessagingClient;


    @GetMapping("/push{id}")
    //@JsonCreator
    public String lineOne(@PathVariable long id) {
        Lesson lesson = lessonService.findById(id);

        //Max 4
        Action action1 = new PostbackAction("訊息1", "text1");
        Action action2 = new PostbackAction("訊息2", "text2");
        Action action3 = new PostbackAction("訊息3", "text3");
        Action action4 = new PostbackAction("訊息4", "text4");

        List<Action> actions = Arrays.asList(action1, action2, action3,action4);

        ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null,"選擇","選擇查詢", actions);

        TemplateMessage templateMessage = new TemplateMessage("Hi",buttonsTemplate);

        TextMessage textMessage = new TextMessage("hello,您關注的課程《"+lesson.getLessonname()+"》已開課了" );
        //PushMessage pushMessage = new PushMessage("U1a64e0bb0eccabef0682243ad1928bf6", textMessage);

        PushMessage pushMessage = new PushMessage("U1a64e0bb0eccabef0682243ad1928bf6", templateMessage);
        PushMessage pushMessage2 = new PushMessage("U1a64e0bb0eccabef0682243ad1928bf6", textMessage);
        //PushMessage pushMessage3 = new PushMessage("U1ea02c48f1dd5fd71945a64c3ef8be7c", templateMessage);

        //U1ea02c48f1dd5fd71945a64c3ef8be7c
        //推送
        CompletableFuture<BotApiResponse> response = lineMessagingClient.pushMessage(pushMessage);
        CompletableFuture<BotApiResponse> response2 = lineMessagingClient.pushMessage(pushMessage2);
        //CompletableFuture<BotApiResponse> response3 = lineMessagingClient.pushMessage(pushMessage2);
        //System.out.println(response);
        return "redirect:/lessonlist";
    }

}
