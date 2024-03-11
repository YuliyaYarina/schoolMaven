package ru.hogwarts.school.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNFE extends RuntimeException{
    public StudentNFE(String message) {
        super(message);
    }

    public StudentNFE() {
    }

}
