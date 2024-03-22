package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createFaculty() throws Exception {
        // given
        Faculty faculty = new Faculty("Griffindor", "Green");

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                faculty,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertNotNull(actualFaculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void getFaculty() throws Exception {
        // given
        Faculty faculty = new Faculty("name", "color");
        faculty = facultyRepository.save(faculty);

        Long facultyId = 1L;

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/" + faculty.getId(),
                Faculty.class
        );
        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertEquals(actualFaculty.getId(), faculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        assertEquals(actualFaculty.getColor(), faculty.getColor());
    }


    @Test
    void updateFaculty() {
        // given
        Faculty faculty = new Faculty("name", "color");
        faculty = facultyRepository.save(faculty);

        Faculty facultyForUpdate = new Faculty("nawName", "newColor");


        // when
        HttpEntity<Faculty> entity = new HttpEntity<>(facultyForUpdate);
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + faculty.getId(),
                HttpMethod.PUT,
                entity,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertEquals(actualFaculty.getId(), faculty.getId());
        assertEquals(actualFaculty.getName(), facultyForUpdate.getName());
        assertEquals(actualFaculty.getColor(), facultyForUpdate.getColor());
    }

    @Test
    void deleteBook() {
        // given
        Faculty faculty = new Faculty("Griffindor", "Green");
        faculty = facultyRepository.save(faculty);

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + faculty.getId(),
                HttpMethod.DELETE,
                null,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Assertions.assertThat(facultyRepository.findById(faculty.getId())).isNotPresent();
    }

    @Test
    void filterColor() {
        // given
        Faculty faculty = new Faculty("Griffindor", "Green");

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                faculty,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertNotNull(actualFaculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void getAllFacultyfilterColorAndName() {
        // given
        Faculty faculty = new Faculty("Griffindor", "Green");

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                faculty,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertNotNull(actualFaculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void getStudent() {
        // given
        Faculty faculty = new Faculty("Griffindor", "Green");

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                faculty,
                Faculty.class
        );

        // then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertNotNull(actualFaculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        org.assertj.core.api.Assertions.assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }
}