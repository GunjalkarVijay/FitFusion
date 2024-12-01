package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.exception.DietNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.DietRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class DietService {
	
	@Autowired
	DietRepository dietRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	public List<Diet> getAllDiets() {
		return dietRepository.findAll();
	}

	public Diet getById(Long id) {
		return dietRepository.findById(id).orElseThrow(()-> new DietNotFoundException("No Diet Found"));
	}

	public void save(DietDto dietDto, Long userId) {
		Diet diet = new Diet();
		diet.setDescription(dietDto.getDescription());
		diet.setName(dietDto.getName());
		User user = userService.getById(userId);
		List<Diet> diets = user.getDiets();
		diets.add(diet);
		user.setDiets(diets);
		diet.setUser(user);
		dietRepository.save(diet);
		userRepository.save(user);
		
	}

	public void update(DietDto dietDto, Long id) {
		Diet diet = getById(id);
		diet.setDescription(dietDto.getDescription());
		diet.setName(dietDto.getName());
		dietRepository.save(diet);
	}

	public void deleteById(Long id) {
		dietRepository.deleteById(id);
	}

}
