/*package nthu.learncloud.handler;

import nthu.learncloud.exception.InvaildRequestException;
import nthu.learncloud.exception.NotFoundException;
import nthu.learncloud.resource.ErrorResouce;
import nthu.learncloud.resource.FieldResource;
import nthu.learncloud.resource.InvaildErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleNotFound(RuntimeException e)
    {
        ErrorResouce errorResouce = new ErrorResouce(e.getMessage());

        return new ResponseEntity<Object>(errorResouce, HttpStatus.NOT_FOUND);
    }


    //處理驗證失敗
    @ExceptionHandler(InvaildRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvaildFound(InvaildRequestException e)
    {
        Errors errors =e.getErros();
        List<FieldResource> fieldResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for(FieldError fieldError : fieldErrors)
        {
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
                    fieldError.getCode(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());
            fieldResources.add(fieldResource);
        }
        InvaildErrorResource invaildErrorResource = new InvaildErrorResource(e.getMessage(),fieldResources);
       return new ResponseEntity<Object>(invaildErrorResource,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public  ResponseEntity<?> handleException(Exception e)
    {
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/
