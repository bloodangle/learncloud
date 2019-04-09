package nthu.learncloud.dto;

import nthu.learncloud.domain.User;

import java.util.List;

public class AjaxResponseBody{


    private String defaultMessage;
    private String field;

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
