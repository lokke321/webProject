package org.webproject.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestException extends RuntimeException {

    public QuestException() {
        super();
    }

    public QuestException(String message) {
        super(message);
    }

}