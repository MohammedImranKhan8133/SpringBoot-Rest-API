package com.imran.crudoperation.controller;
import com.imran.crudoperation.entity.Student;
import com.imran.crudoperation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private List<Student> students;

    @BeforeEach
    void setUp() {
        students = Arrays.asList(
                Student.builder().studentId(1).studentName("John").studentCourse("History").build(),
                Student.builder().studentId(2).studentName("Jane").studentCourse("Math").build(),
                Student.builder().studentId(3).studentName("Bob").studentCourse("Science").build()
        );
    }

    @Test
    void testGetAllStudent() throws Exception {
        Mockito.when(studentService.getAllStudent())
                .thenReturn(students);

        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].studentName").value("John"))
                .andExpect(jsonPath("$[1].studentName").value("Jane"))
                .andExpect(jsonPath("$[2].studentName").value("Bob"));
    }
    @Test
    void testGetAllStudentEmpty() throws Exception {
        Mockito.when(studentService.getAllStudent())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    void testGetAllStudentException() throws Exception {
        Mockito.when(studentService.getAllStudent())
                .thenThrow(new RuntimeException("Database connection error"));

        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Database connection error"));
    }

}
