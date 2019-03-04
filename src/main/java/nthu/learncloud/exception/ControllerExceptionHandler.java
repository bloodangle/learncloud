package nthu.learncloud.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


//全域錯誤
@ControllerAdvice
public class ControllerExceptionHandler {

    private  final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    //,  SQLException.class
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
