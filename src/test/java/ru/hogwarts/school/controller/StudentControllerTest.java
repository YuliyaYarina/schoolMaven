package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

//    @MockBean
//    private StudentRepository studentRepository;

    @MockBean
    private AvatarService avatarService;

//    @Autowired
//    private TestRestTemplate restTemplate;


//    @InjectMocks
//    private StudentController studentController;

    @Test
    void add() throws Exception {
        // given
        Long studentId = 1L;
        Student student = new Student("Ivan", 20);

//        Student savedStudent = new Student("Ivan2", 22);

//        savedStudent.setId(studentId);

        when(studentService.add(student)).thenReturn(student);
        // when
        ResultActions perform = mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
        // then
        perform
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andDo(print());
        }

        @Test
        void get() throws Exception {
            // given
             Long studentId = 1L;
            Student student = new Student("Ivan", 20);

            when(studentService.get(studentId)).thenReturn(student);
            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/" +  studentId));

            // then
            perform
                    .andExpect(jsonPath("$.name").value(student.getName()))
                    .andExpect(jsonPath("$.age").value(student.getAge()))
                    .andDo(print());
        }


        @Test
        void update() throws Exception {
            // given
            Long studentId = 1L;
            Student student = new Student("Ivan", 20);

            when(studentService.update(studentId, student)).thenReturn(student);
            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/student/{id}", studentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(student)));

            // then
            perform
                    .andExpect(jsonPath("$.name").value(student.getName()))
                    .andExpect(jsonPath("$.age").value(student.getAge()))
                    .andDo(print());
        }

        @Test
        void deleteStudent() throws Exception {
            long studentId = 1L;
            Student student = new Student("Ivan", 20);

//        when(studentService.delete(studentId)).thenReturn(student);
//        // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/student/{Id}", studentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(student)));


            // then
            perform
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                .andExpect(jsonPath("$.name").value(student.getName()))
//                .andExpect(jsonPath("$.age").value(student.getAge()))
                    .andDo(print());
        }

        @Test
        void findAllByAgeStudent() throws Exception{
            // given
            Long studentId = 1L;
            Student student = new Student("Ivan", 20);
            String studentName = "name";

            when(studentService.get(studentId)).thenReturn(student);

            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/" +  studentId));

            // then
            perform
                    .andExpect(jsonPath("$.name").value(student.getName()))
                    .andExpect(jsonPath("$.age").value(student.getAge()))
                    .andDo(print());
//            Assertions
//                    .assertThat(this.restTemplate.getForObject("http://localhost:", String.class))
//                    .isNotNull();

        }

        @Test
        void getByAge() throws Exception {
            // given
            int studentAge = 20;
            int studentAgeNew = 44;
            Student student = new Student("Ivan", 20);
            String studentName = "name";

            when(studentService.getByAge(studentAge)).thenReturn(Collections.singletonList(student));

            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/getByAge?age=" +  studentAge));

            // then
            perform
//                    .andExpect(jsonPath("$.id").value(student.getId()))
                    .andExpect(jsonPath("$.name").value(student.getName()))
                    .andExpect(jsonPath("$.age").value(student.getAge()))
                    .andDo(print());

        }

        @Test
        void getByAgeBetween() {
        }

        @Test
        void getFaculty() {
        }

        @Test
        void uploadAvatar() {
    }
}