package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

//    @BeforeEach
//    private void clearDataBase(){
//        facultyRepository.deleteAll();
//    }

    @Test
    void createStudentTest() throws Exception {
        // given
        Student student = new Student("name", 20);

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                student,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        assertNotNull(actualStudent.getId());
        assertEquals(actualStudent.getName(), student.getName());
        org.assertj.core.api.Assertions.assertThat(actualStudent.getAge()).isEqualTo(student.getAge());

//        facultyRepository.deleteById(actualFaculty.getId());
    }

    @Test
    void getStudentTest() throws Exception {
        // given
        Student student = new Student("name", 20);
        student = studentRepository.save(student);

        Long facultyId = 1L;

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/student/" + student.getId(),
                Student.class
        );
        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        assertEquals(actualStudent.getId(), student.getId());
        assertEquals(actualStudent.getName(), student.getName());
        assertEquals(actualStudent.getAge(), student.getAge());

//        assertNotNull(actualFaculty.getId());
//        assertEquals(actualFaculty.getName(), faculty.getName());
//        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }


    @Test
    void updateStudentTest() {
        // given
        Student student = new Student("name", 20);
        student = studentRepository.save(student);

        Student studentForUpdate = new Student("nawName", 1);


        // when
        HttpEntity<Student> entity = new HttpEntity<>(studentForUpdate);
        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/student/" + student.getId(),
                HttpMethod.PUT,
                entity,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualFaculty = studentResponseEntity.getBody();
        assertEquals(actualFaculty.getId(), student.getId());
        assertEquals(actualFaculty.getName(), studentForUpdate.getName());
        assertEquals(actualFaculty.getAge(), studentForUpdate.getAge());

//        assertNotNull(actualFaculty.getId());
//        assertEquals(actualFaculty.getName(), faculty.getName());
//        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void deleteStudent() {
        // given
        Student student = new Student("name", 20);
        student = studentRepository.save(student);

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/student/" + student.getId(),
                HttpMethod.DELETE,
                null,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Assertions.assertThat(studentRepository.findById(student.getId())).isNotPresent();
//        Faculty actualFaculty = facultyResponseEntity.getBody();
//        assertNotNull(actualFaculty.getId());
//        assertEquals(actualFaculty.getName(), faculty.getName());
//        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void filterAge() {
        // given
        Student student =  new Student("name", 20);

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                student,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        assertNotNull(actualStudent.getId());
        assertEquals(actualStudent.getName(), student.getName());
        org.assertj.core.api.Assertions.assertThat(actualStudent.getAge()).isEqualTo(student.getAge());
    }

    @Test
    void getAllFacultyfilterColorAndName() {
        // given
        Student student =  new Student("name", 20);

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                student,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        assertNotNull(actualStudent.getId());
        assertEquals(actualStudent.getName(), student.getName());
        org.assertj.core.api.Assertions.assertThat(actualStudent.getAge()).isEqualTo(student.getAge());
    }

    @Test
    void getStudent() {
        // given
        Student student =  new Student("name", 20);

        // when
        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                student,
                Student.class
        );

        // then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        assertNotNull(actualStudent.getId());
        assertEquals(actualStudent.getName(), student.getName());
        org.assertj.core.api.Assertions.assertThat(actualStudent.getAge()).isEqualTo(student.getAge());
    }
}