package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    private TestRestTemplate restTemplate;

    @Test
    void add() {
    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteBStudent() {
    }

    @Test
    void findAllByAgeStudent() {
            Assertions
                    .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class))
                    .isNotNull();
    }

    @Test
    void getByAge() {
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