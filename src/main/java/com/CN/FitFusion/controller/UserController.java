package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//ADMIN API'S
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public User getById(@PathVariable Long id){
		return userService.getById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public void updateById(@RequestBody UserDto user,@PathVariable Long id){
		userService.updateById(user,id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id){
		userService.deleteById(id);
	}
	
	//CUSTOMER API'S
	
	@GetMapping("/exercise/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('CUSTOMER')")
	public List<Exercise> getExerciseById(@PathVariable Long id){
		return userService.getExerciseById(id);
	}
	
	@GetMapping("/diet/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('CUSTOMER')")
	public List<Diet> getDietById(@PathVariable Long id){
		return userService.getDietById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	public void registerUser(@RequestBody UserDto user) {
		userService.createUser(user);
    }
	
	
	
}
