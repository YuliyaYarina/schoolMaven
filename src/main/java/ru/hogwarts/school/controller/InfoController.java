package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.StudentService;

@RestController
public class InfoController {
    private StudentService studentService;

    @Value("${server.port}")
    private Integer port;
    @GetMapping("port")
    public int getPort(){
        return port;
    }

}
