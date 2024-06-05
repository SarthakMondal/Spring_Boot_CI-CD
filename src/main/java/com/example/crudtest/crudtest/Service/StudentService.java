package com.example.crudtest.crudtest.Service;

import java.util.List;
import java.util.Optional;

import com.example.crudtest.crudtest.Model.StudentModel;
import com.example.crudtest.crudtest.Repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<?> addStudent(StudentModel studentModel) {
        ResponseEntity<?> response = null;

        try {
            studentRepo.save(studentModel);
            response = new ResponseEntity<>(studentModel, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }


    public ResponseEntity<?> getAllStudents() {
        ResponseEntity<?> response = null;

        try {
            List<StudentModel> students = studentRepo.findAll();
            response = new ResponseEntity<>(students, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
