package com.example.crudtest.crudtest.Repo;

import com.example.crudtest.crudtest.Model.StudentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Long>
{
    
}
