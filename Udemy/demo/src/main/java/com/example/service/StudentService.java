package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repo.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
	
	
	public  Student createStudentReq(CreateStudentRequest createStudentRequest) {
		Student student= new Student(createStudentRequest);
		student=studentRepo.save(student);
		return student;
	}
	
	public  Student createStudent(Student student) {
		Student res=studentRepo.save(student);
		return res;
	}
	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
		Student student=studentRepo.findById(updateStudentRequest.getId()).get();
		if(updateStudentRequest.getFirstName()!=null && !updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		
		student=studentRepo.save(student);
		return student;
	}
	
	public String deleteStudent(long id) {
		studentRepo.deleteById(id);
		return "Student delete successfully";
	}
	
	public List<Student> getByFirstName(String firstName){
		return studentRepo.findByFirstName(firstName);
	}
	
	public Student getByFirstNameAndLastName(String firstName,String lastName) {
		return  studentRepo.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Student> getByFirstNameOrLastName(String firstName,String lastName) {
		return  studentRepo.findByFirstNameOrLastName(firstName,lastName);
	}
	
	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest ) {
		return  studentRepo.findByFirstNameIn(inQueryRequest.getFirstName());
	}
	
	public List<Student> getAllStudentWithPagination(int pageNo,int pageSize){
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return studentRepo.findAll(pageable).getContent();
	}
	
	public List<Student> getAllStudentWithSorting(){
		Sort sort=Sort.by(Sort.Direction.ASC,"firstName","email");
		return studentRepo.findAll(sort);
	}
	
	public List<Student> like(String firstName ) {
		return  studentRepo.findByFirstNameContains(firstName);
	}
	
	public List<Student> startsWith(String firstName ) {
		return  studentRepo.findByFirstNameStartsWith(firstName);
	}
	
	public List<Student> endsWith(String firstName ) {
		return  studentRepo.findByFirstNameEndsWith(firstName);
	}
	
}
