package com.example.cruddto.controller;

import com.example.cruddto.dto.FacultyDto;
import com.example.cruddto.dto.UniversityAddressDTO;
import com.example.cruddto.entity.Address;
import com.example.cruddto.entity.Faculty;
import com.example.cruddto.entity.University;
import com.example.cruddto.repository.FacultyRepository;
import com.example.cruddto.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/faculty",method = RequestMethod.POST)
    public String addUniversity(@RequestBody FacultyDto facultyDto){
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversity_id());
        if (optionalUniversity.isPresent()) {
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "save Faculty";
        }else {
            return "University Not Found";
        }
    }

    @RequestMapping(value = "/faculty")
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    @RequestMapping(value = "/faculty/{university_id}", method = RequestMethod.GET)
    public List<Faculty> getFaculty(@PathVariable Integer university_id){
        List<Faculty> byUniversityId = facultyRepository.findAllByUniversityId(university_id);
        return byUniversityId;
    }

}
