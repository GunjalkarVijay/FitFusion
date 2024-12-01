package com.CN.FitFusion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String email;
	private String password;
	private int age;
	private String gender;
	private long contactNo;
	private String userType;
}
