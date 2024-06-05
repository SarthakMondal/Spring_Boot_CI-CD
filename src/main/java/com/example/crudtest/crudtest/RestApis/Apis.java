package com.example.crudtest.crudtest.RestApis;

import com.example.crudtest.crudtest.Model.StudentModel;
import com.example.crudtest.crudtest.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/backend")
public class Apis {
    @Autowired
    StudentService studentService;

    @PostMapping(path = "/addstudent")
    public ResponseEntity<?> addStudent(@RequestBody StudentModel studentModel) {
        return this.studentService.addStudent(studentModel);
    }

    @GetMapping(path = "/getstudents")
    public ResponseEntity<?> getAllStudents() {
        return this.studentService.getAllStudents();
    }

}
