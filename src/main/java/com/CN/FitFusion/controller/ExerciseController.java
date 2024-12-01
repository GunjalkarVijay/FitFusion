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

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	@PreAuthorize("hasRole('TRAINER')")
	public List<Exercise> getAllExercise(){
		return exerciseService.getAllExercise();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Exercise getById(@PathVariable Long id){
		return exerciseService.getById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create/{userId}")
	@PreAuthorize("hasRole('TRAINER')")
	public void save(@RequestBody ExerciseDto exerciseDto,@PathVariable Long userId){
		exerciseService.save(exerciseDto,userId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void update(@RequestBody ExerciseDto exerciseDto,@PathVariable Long id){
		exerciseService.update(exerciseDto,id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void deleteById(@PathVariable Long id){
		exerciseService.deleteById(id);
	}
}
