package com.example.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {

	@NotNull(message="id can't be empty")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@JsonProperty("Email_Address")
	private String email;
	
	
}
