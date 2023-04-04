package com.imran.crudoperation.service;

import com.imran.crudoperation.entity.Student;
import com.imran.crudoperation.repository.StudentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    @Autowired
    private StudentRep studentRep;
    public Student saveStudent(Student student) {
        return studentRep.save(student);
    }

    public List<Student> getAllStudent() {
        return studentRep.findAll();
    }

    public Student getStudentById(int id) {
        return studentRep.findById(id).get();
    }

    public Student getStudentByName(String name) {
        return studentRep.findByStudentNameIgnoreCase(name);
    }

    public void deleteStudent(int id) {
        studentRep.deleteById(id);
    }

    public Student updateStudent(int id, Student student) {
        Student studentDB=studentRep.findById(id).get();

        if(Objects.nonNull(student.getStudentName()) &&
                !"".equalsIgnoreCase(student.getStudentName())) {
           studentDB.setStudentName(student.getStudentName());
        }

        if(Objects.nonNull(student.getStudentCourse()) &&
                !"".equalsIgnoreCase(student.getStudentCourse())) {
           studentDB.setStudentCourse(student.getStudentCourse());
        }

        return studentRep.save(studentDB);
    }
}
