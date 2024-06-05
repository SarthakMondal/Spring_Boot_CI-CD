package com.example.crudtest.crudtest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "std_record")
@Getter
@Setter
@ToString
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_id")
    private Long id;

    @Column(name = "std_name")
    private String name;

    @Column(name = "std_year")
    private String year;

    @Column(name = "std_marks")
    private int marks;

}
