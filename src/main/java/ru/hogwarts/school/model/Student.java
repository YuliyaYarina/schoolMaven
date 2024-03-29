package ru.hogwarts.school.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;



@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;


    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Student(int age, Faculty faculty) {
        this.age = age;
        this.faculty = faculty;
    }

    public String getAvatarsDir() {
        return avatarsDir;
    }

    public void setAvatarsDir(String avatarsDir) {
        this.avatarsDir = avatarsDir;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                ", avatarsDir='" + avatarsDir + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(faculty, student.faculty) && Objects.equals(avatarsDir, student.avatarsDir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, faculty, avatarsDir);
    }

}
