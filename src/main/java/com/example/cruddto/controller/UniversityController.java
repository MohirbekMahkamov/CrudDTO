package com.example.cruddto.controller;

import com.example.cruddto.dto.UniversityAddressDTO;
import com.example.cruddto.entity.Address;
import com.example.cruddto.entity.University;
import com.example.cruddto.repository.AddressRepository;
import com.example.cruddto.repository.UniversityRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity(){
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityAddressDTO universityAddressDTO){
        boolean byName = universityRepository.existsByName(universityAddressDTO.getName());
        if (!byName) {
            University university = new University();
            university.setName(universityAddressDTO.getName());
            Address address = new Address();
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            address.setStreet(universityAddressDTO.getStreet());
            addressRepository.save(address);
            university.setAddress(address);
            universityRepository.save(university);

            return "Universitet Qo'shildi.";
        }else {
            return "Bunday nomli University allaqachon bor bo'lishi mumkin";
        }
    }

    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@RequestBody UniversityAddressDTO universityAddressDTO, @PathVariable Integer id){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();

            university.setName(universityAddressDTO.getName());
            Address address = new Address();
            address.setStreet(universityAddressDTO.getStreet());
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            addressRepository.save(address);
            university.setAddress(address);
            universityRepository.save(university);

        }
        return "Edited University";
    }



}
