package com.imran.crudoperation.controller;

import com.imran.crudoperation.entity.Student;
import com.imran.crudoperation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/student")
    public Student saveStudent(Student student) {
        return studentService.saveStudent(student);
    }
    @GetMapping("/student")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudentById(id);
    }
    @GetMapping("/studentByName/{name}")
    public Student getStudentByName(@PathVariable("name") String name){
        return studentService.getStudentByName(name);
    }
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable("id") int id,Student student){
        return studentService.updateStudent(id,student);
    }
    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        studentService.deleteStudent(id);
        return "This "+id+" id student data has been deleted";
    }
}
