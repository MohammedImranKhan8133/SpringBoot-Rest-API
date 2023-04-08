package com.imran.crudoperation.repository;

import com.imran.crudoperation.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepTest {

    @Autowired
    private StudentRep studentRep;

    @Test
    public void findByIdShouldReturnStudentWithMatchingId() {
        // Given
        Student student1 = Student.builder()
                .studentName("John Doe")
                .studentCourse("Computer Science")
                .build();
        Student student2 = Student.builder()
                .studentName("Jane Doe")
                .studentCourse("Mathematics")
                .build();
        studentRep.saveAll(List.of(student1, student2));

        // When
        Optional<Student> optionalStudent = studentRep.findById(student2.getStudentId());

        // Then
        assertTrue(optionalStudent.isPresent());
        assertEquals(student2, optionalStudent.get());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalForNonExistingId() {
        // Given
        int nonExistingId = 1;

        // When
        Optional<Student> optionalStudent = studentRep.findById(nonExistingId);

        // Then
        assertFalse(optionalStudent.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnStudentWithDifferentId() {
        // Given
        Student student1 = Student.builder()
                .studentName("John Doe")
                .studentCourse("Computer Science")
                .build();
        Student student2 = Student.builder()
                .studentName("Jane Doe")
                .studentCourse("Mathematics")
                .build();
        studentRep.saveAll(List.of(student1, student2));

        // When
        Optional<Student> optionalStudent = studentRep.findById(student1.getStudentId() - 1);

        // Then
        assertFalse(optionalStudent.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnDeletedStudent() {
        // Given
        Student student = Student.builder()
                .studentName("John Doe")
                .studentCourse("Computer Science")
                .build();
        studentRep.save(student);

        // When
        studentRep.delete(student);
        Optional<Student> optionalStudent = studentRep.findById(student.getStudentId());

        // Then
        assertFalse(optionalStudent.isPresent());
    }
}
