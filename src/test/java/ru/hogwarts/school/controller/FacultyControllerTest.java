package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FacultyService facultyService;

    @Test
    void addFaculty() throws Exception {
        Faculty faculty = new Faculty("Grifindor","Green");

        when(facultyService.add(faculty)).thenReturn(faculty);
        // when
        ResultActions perform = mockMvc.perform(post("/faculty")
                .content(objectMapper.writeValueAsString(faculty)));
        // then
        perform
//                .andExpect(jsonPath("$.name").value(faculty.getName()))
//                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }

    @Test
    void getFaculty() throws Exception {
        // given
        Long id = 1L;
        Faculty faculty = new Faculty("Grifindor","Green");

        when(facultyService.getFaculty(id)).thenReturn(faculty);
        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/faculty/" +  id));

        // then
        perform
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }

    @Test
    void editFaculty() throws Exception  {
        // given
        Long id = 1L;
        Faculty faculty = new Faculty("Grifindor","Green");

        when(facultyService.editFaculty(id, faculty)).thenReturn(faculty);
        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/faculty/{id}",  id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        // then
        perform
//                .andExpect(jsonPath("$.name").value(faculty.getName()))
//                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }

    @Test
    void deleteBook() throws Exception {
        // given
        Long id = 1L;
        Faculty faculty = new Faculty("Grifindor","Green");

        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/{Id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        // then
        perform
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void filterColor() throws Exception {
        // given
        String colorF = "Red";
        Long id = 1L;
        Faculty faculty = new Faculty("Grifindor","Green");

        JSONObject studentObject = new JSONObject();

        when(facultyService.getColorFaculty(colorF)).thenReturn(Collections.singletonList(faculty));

        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/color" +  colorF));

        // then
        perform
                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
                .andExpect(jsonPath("$[0].age").value(faculty.getColor()))
                .andDo(print());
    }

}