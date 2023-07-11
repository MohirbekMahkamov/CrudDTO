package com.example.cruddto.controller;

import com.example.cruddto.entity.Group;
import com.example.cruddto.entity.Student;
import com.example.cruddto.dto.StudentDTO;
import com.example.cruddto.repository.GroupRepository;
import com.example.cruddto.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    @GetMapping
    public List<Student> allStudents(){

        return studentRepository.findAll();

    }
    @GetMapping("/page")
    public Page<Student> getStudents(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,10);

        Page<Student> all = studentRepository.findAll(pageable);
        return all;

    }

    @PostMapping
    public String addStudent(@RequestBody StudentDTO studentDTO){
        Student student = new Student();
        student.setFullName(studentDTO.getFullName());
        student.setBirthday(studentDTO.getBirthday());
        Optional<Group> byId = groupRepository.findById(studentDTO.getGroup_id());
        if (byId.isPresent()) {
            student.setGroup(byId.get());
        }else {
            return "Bunday id lik guruh yo'q";
        }
        studentRepository.save(student);
        return "Student Qo'shildi";

    }



}
