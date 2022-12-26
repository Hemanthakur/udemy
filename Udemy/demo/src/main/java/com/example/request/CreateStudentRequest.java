package com.example.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {


	@NotBlank(message="First Name can't be empty")
	private String firstName;
	
	private String lastName;
	
	@JsonProperty("Email_Address")
	private String email;
	
	
}
