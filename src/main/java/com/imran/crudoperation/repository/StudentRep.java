package com.imran.crudoperation.repository;

import com.imran.crudoperation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRep extends JpaRepository<Student,Integer> {
    public Student findByStudentNameIgnoreCase(String name);
}
