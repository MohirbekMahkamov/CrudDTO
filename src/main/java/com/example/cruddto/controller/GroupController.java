package com.example.cruddto.controller;

import com.example.cruddto.dto.GroupDTO;
import com.example.cruddto.entity.Faculty;
import com.example.cruddto.entity.Group;
import com.example.cruddto.repository.FacultyRepository;
import com.example.cruddto.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String addGroup(@RequestBody GroupDTO groupDTO){
        Group group  = new Group();
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFaculty_id());
        if (optionalFaculty.isPresent()){
            group.setName(groupDTO.getName());
            group.setFaculty(optionalFaculty.get());
            groupRepository.save(group);
            return "Added Faculty";
        } else {
            return "Not Fount Faculty";
        }
    }
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public List<Group> getGroups(){
        return groupRepository.findAll();
    }


}
