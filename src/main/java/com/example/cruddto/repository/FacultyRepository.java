package com.example.cruddto.repository;

import com.example.cruddto.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty , Integer> {
    List<Faculty> findAllByUniversityId(Integer id);

}
