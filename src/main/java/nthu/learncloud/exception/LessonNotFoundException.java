package nthu.learncloud.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
    }

    public LessonNotFoundException(String message) {
        super(message);
    }

    public LessonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
