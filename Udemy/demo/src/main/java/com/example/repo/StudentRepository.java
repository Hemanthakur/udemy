package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	List<Student> findByFirstName(String firstName);
	
	Student findByFirstNameAndLastName(String firstName,String lastName);
	
	List<Student> findByFirstNameOrLastName(String firstName,String lastName);
	
	List<Student> findByFirstNameIn(List<String> firstName);
	
	List<Student> findByFirstNameContains(String firstName);
	
	List<Student> findByFirstNameStartsWith(String firstName);
	
	List<Student> findByFirstNameEndsWith(String firstName);
}
