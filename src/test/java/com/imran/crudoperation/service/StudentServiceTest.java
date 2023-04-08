package com.imran.crudoperation.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.imran.crudoperation.entity.Student;
import com.imran.crudoperation.repository.StudentRep;
import com.imran.crudoperation.service.StudentService;

public class StudentServiceTest {

    @Mock
    private StudentRep studentRepo;

    @InjectMocks
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        int studentId = 1;
        Student expectedStudent = new Student(1, "John Doe", "Computer Science");
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        // Act
        Student actualStudent = studentService.getStudentById(studentId);

        // Assert
        assertEquals(expectedStudent, actualStudent);
        verify(studentRepo, times(1)).findById(studentId);
    }
    @Test
    public void testGetStudentById_ValidId() {
        // Arrange
        int studentId = 1;
        Student expectedStudent = new Student(1, "John Doe", "Computer Science");
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        // Act
        Student actualStudent = studentService.getStudentById(studentId);

        // Assert
        assertEquals(expectedStudent, actualStudent);
        verify(studentRepo, times(1)).findById(studentId);
    }
    @Test(expected = NoSuchElementException.class)
    public void testGetStudentById_InvalidId() {
        // Arrange
        int studentId = 100;
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());

        // Act
        Student actualStudent = studentService.getStudentById(studentId);

        // Assert
        // An exception should be thrown in this case
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetStudentById_NullInput() {
        // Arrange
        Integer studentId = null;

        // Act
        Student actualStudent = studentService.getStudentById(studentId);

        // Assert
        // An exception should be thrown in this case
    }
    @Test
    public void testGetStudentById_MultipleCalls() {
        // Arrange
        int studentId1 = 1;
        Student expectedStudent1 = new Student(1, "John Doe", "Computer Science");
        when(studentRepo.findById(studentId1)).thenReturn(Optional.of(expectedStudent1));

        int studentId2 = 2;
        Student expectedStudent2 = new Student(2, "Jane Smith", "Biology");
        when(studentRepo.findById(studentId2)).thenReturn(Optional.of(expectedStudent2));

        // Act
        Student actualStudent1 = studentService.getStudentById(studentId1);
        Student actualStudent2 = studentService.getStudentById(studentId2);

        // Assert
        assertEquals(expectedStudent1, actualStudent1);
        assertEquals(expectedStudent2, actualStudent2);
        verify(studentRepo, times(1)).findById(studentId1);
        verify(studentRepo, times(1)).findById(studentId2);
    }


}
