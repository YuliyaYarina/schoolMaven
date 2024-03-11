package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolMavenApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoadsS() throws Exception {
        org.assertj.core.api.Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    void contextLoadsF() throws Exception {
        org.assertj.core.api.Assertions.assertThat(facultyController).isNotNull();
    }
//    public void testDefaultMassage() throws Exception{
//        org.assertj.core.api.Assertions
//                .assertThat(this.restTemplate.getForObject());
//    }
//    @Test
//    public void testAuthor() throws Exception {
//        org.assertj.core.api.Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?name=Gari", String.class))
//                .isEqualTo(studentController.findAllByAgeStudent(Gari));
//    }


}
