package ru.hogwarts.school;

//"для наглядности"

//
//select * from student where age > 9 SemanticContext.AND age<20;
//
//select name from student ;
//
//select * from student where name like '%D%';
//
//select * from student where age < id;
//
//select * from student ORDER BY age;


import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;

//@Test
//void getFaculty() throws Exception {
//    // given
//    Long studentId = 1L;
//    Faculty faculty = new Faculty("F","Ivan");
//
//    when(studentService.getFaculty(studentId)).thenReturn(faculty);
//    // when
//
//    ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/faculty/" +  studentId));
//    // then
//    perform
////                .andExpect(jsonPath("$.name").value(faculty.getName()))
////                .andExpect(jsonPath("$.age").value(faculty.getColor()))
//            .andDo(print());
//}\\

//@Test
//void getAllFacultyfilterColorAndName() throws Exception  {
//    // given
//    String color = "Red";
//    String name = "Grif";
//
//    Faculty faculty = new Faculty("Grifindor","Green");
//
////        JSONObject studentObject = new JSONObject();
//    when(facultyService.findByName(name)).thenReturn(Collections.singletonList(faculty));
//    when(facultyService.findByColor(color)).thenReturn(Collections.singletonList(faculty));
//
//    // when
//    ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("faculty?name=" + name));
//    ResultActions perform1 = mockMvc.perform(MockMvcRequestBuilders.get("faculty?color=" + color));
//
//    // then
//    perform
//            .andExpect(jsonPath("$[0].name").value(faculty.getName()))
//            .andExpect(jsonPath("$[0].age").value(faculty.getColor()))
//            .andDo(print());
//}
//
//@Test
//void getStudent() throws Exception {
//    // given
//    Long id = 1L;
//    Faculty faculty = new Faculty("Grifindor", "Green");
//
//    when(facultyService.getStudents(id)).thenReturn((Collection<Student>) faculty);
//    // when
//    ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/{id}/faculty", id));
//
//    // then
//    perform
//            .andExpect(jsonPath("$.name").value(faculty.getName()))
//            .andExpect(jsonPath("$.age").value(faculty.getColor()))
//            .andDo(print());
//}