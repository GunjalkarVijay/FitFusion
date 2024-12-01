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

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.service.DietService;

@RestController
@RequestMapping("/diet")
public class DietController {

	@Autowired
	DietService dietService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	@PreAuthorize("hasRole('TRAINER')")
	public List<Diet> getAllDiets(){
		return dietService.getAllDiets();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Diet getById(@PathVariable Long id){
		return dietService.getById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create/{userId}")
	@PreAuthorize("hasRole('TRAINER')")
	public void save(@RequestBody DietDto dietDto,@PathVariable Long userId){
		dietService.save(dietDto,userId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void update(@RequestBody DietDto dietDto,@PathVariable Long id){
		dietService.update(dietDto,id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void deleteById(@PathVariable Long id){
		dietService.deleteById(id);
	}
	
}
