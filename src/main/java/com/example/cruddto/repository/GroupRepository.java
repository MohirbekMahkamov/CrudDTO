package com.example.cruddto.repository;

import com.example.cruddto.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {

    boolean existsByFaculty_Id(Integer faculty_id);
}
