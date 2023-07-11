package com.example.cruddto.repository;

import com.example.cruddto.entity.Group;
import com.example.cruddto.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
