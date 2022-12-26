package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/all")
	public List<Student> getall() {
		return studentService.getAllStudents();
	}

	@GetMapping("/allres")
	public List<StudentResponse> getAllStudentResponse() {
		List<Student> sl = studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		sl.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}

	@GetMapping("/allfirst/{firstName}")
	public List<StudentResponse> getAllByFirstName(@PathVariable String firstName) {
		List<Student> sl = studentService.getByFirstName(firstName);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		sl.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}

	@GetMapping("/getand/{firstName}/{lastName}")
	public Student getVyFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
		return studentService.getByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/getor/{firstName}/{lastName}")
	public List<StudentResponse> getbyFirstNameOrLastName(@PathVariable String firstName,
			@PathVariable String lastName) {
		List<Student> sl = studentService.getByFirstNameOrLastName(firstName, lastName);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		sl.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("/getin")
	public List<StudentResponse> getbyFirstNameOrLastName(@RequestBody InQueryRequest inQueryRequest) {
		List<Student> sl = studentService.getByFirstNameIn(inQueryRequest);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		sl.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}

	//Pagination
	@GetMapping("/getStudentWithPagination")
	public List<StudentResponse> getAllStudentWithPagination(@RequestParam int pageNo,@RequestParam int pageSize){
		List<Student> sl = studentService.getAllStudentWithPagination(pageNo, pageSize);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		sl.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	//Sorting
		@GetMapping("/getStudentWithSorting")
		public List<StudentResponse> getAllStudentWithSorting(){
			List<Student> sl = studentService.getAllStudentWithSorting();
			List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
			sl.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			return studentResponseList;
		}
		
		//Like Query
		@GetMapping("/like/{firstName}")
		public List<StudentResponse> like(@PathVariable String firstName) {
			List<Student> sl = studentService.like(firstName);
			List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
			sl.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			return studentResponseList;
		}
		//StartsWtih
		@GetMapping("/start/{firstName}")
		public List<StudentResponse> statsWith(@PathVariable String firstName) {
			List<Student> sl = studentService.startsWith(firstName);
			List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
			sl.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			return studentResponseList;
		}
		//endswith
		@GetMapping("/end/{firstName}")
		public List<StudentResponse> EndsWith(@PathVariable String firstName) {
			List<Student> sl = studentService.endsWith(firstName);
			List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
			sl.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			return studentResponseList;
		}

	@PostMapping("/createres")
	public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = studentService.createStudentReq(createStudentRequest);
		return new StudentResponse(student);
	}

	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		Student student1 = studentService.createStudent(student);
		return student1;
	}

	@PutMapping("/update")
	public StudentResponse updtaeStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		Student student = studentService.updateStudent(updateStudentRequest);
		return new StudentResponse(student);
	}
//	RequestParam
//	@DeleteMapping("/delete")
//	public String deleteStudent(@RequestParam long id) {
//		return studentService.deleteStudent(id);
//	}

	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable long id) {
		return studentService.deleteStudent(id);
	}
}
