package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Student;
import com.example.helloworldapi.repository.StudentSimpleRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
    private final StudentSimpleRepo studentSimpleRepo = new StudentSimpleRepo();
    @GetMapping(value = "")
    public List<Student> showAll(@RequestParam(name = "filterName",required = false) String filter){
        if(filter == null){
            return studentSimpleRepo.findAll();
        } else {
            return studentSimpleRepo.findWhereNameLike(filter);
        }
    }

    @PostMapping(value = "")
    public Student post(@RequestBody Student student){
        return studentSimpleRepo.save(student);
    }

    @GetMapping(value = "/{id}")
    public List<Student> show(@PathVariable Long id){
        return studentSimpleRepo.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        studentSimpleRepo.deleteById(id);
    }
}
