package ru.hogwarts.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @MockBean
    private AvatarService avatarService;


    @Test
    void add() throws Exception {
        // given
        Student student = new Student("Ivan", 20);

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

//        // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/student/{Id}", studentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(student)));

            // then
            perform
                    .andExpect(status().is2xxSuccessful())
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
        }

        @Test
        void getByAge() throws Exception {
            // given
            int studentAge = 20;
            Student student = new Student("Ivan", 20);
            student.setId(1L);
            String studentName = "Petr";

            JSONObject studentObject = new JSONObject();
            studentObject.put("name", studentName);
            studentObject.put("age", studentAge);

            when(studentService.getByAge(studentAge)).thenReturn(Collections.singletonList(student));

            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/getByAge?age=" +  studentAge));

            // then
            perform
                    .andExpect(jsonPath("$[0].name").value(student.getName()))
                    .andExpect(jsonPath("$[0].age").value(student.getAge()))
                    .andDo(print());
        }

        @Test
        void getByAgeBetween() throws Exception {
            // given
            int ageFrom = 20;
            int ageTo = 30;
            Student student = new Student("Ivan", 20);
//            Student student1 = new Student("Jana", 58);

            student.setId(1L);


            JSONObject studentObject = new JSONObject();


            when(studentService.getByAgeBetween(ageFrom, ageTo)).thenReturn(Collections.singletonList(student));

            // when
            MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/student/ageMinMax?ageFrom=" + ageFrom + "&ageTo=" + ageTo)
                            .content(studentObject.toString());
            // then
            mockMvc.perform(mockHttpServletRequestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name").value(student.getName()))
                    .andExpect(jsonPath("$[0].age").value(student.getAge()))
                    .andDo(print());
        }

    @Test
    void getFaculty() throws Exception {
        // given
        Long studentId = 1L;
        Faculty faculty = new Faculty("F","Ivan");

        when(studentService.getFaculty(studentId)).thenReturn(faculty);
        // when

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/faculty/" +  studentId));
        // then
        perform
//                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
//                .andExpect(jsonPath("$.age").value(faculty.getColor()))
                .andDo(print());
    }

        @Test
        void uploadAvatar() throws Exception {
            long studentId = 1L;
            Student student = new Student("Ivan", 20);
            String avatar = MediaType.MULTIPART_FORM_DATA_VALUE ;

            // when
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/{studentId}/avatar?studentId=", studentId + "&avatar=" + avatar)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(student)));

            // then
            perform
//                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
    }
}