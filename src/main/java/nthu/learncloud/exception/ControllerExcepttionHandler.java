package nthu.learncloud.exception;

import nthu.learncloud.controll.LessonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


//全域錯誤
@ControllerAdvice
public class ControllerExcepttionHandler {

    private  final Logger logger = LoggerFactory.getLogger(ControllerExcepttionHandler.class);

    //, LessonNotFoundException.class, SQLException.class
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception e)
    {
        logger.error("Request URL:{} , Exception:{}",request.getRequestURL(),e.getMessage());
        ModelAndView mav =new ModelAndView();

        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");

        return mav;
    }
}
