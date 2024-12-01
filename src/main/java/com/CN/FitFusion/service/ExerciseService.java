package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.exception.ExerciseNotFoundException;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.ExerciseRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class ExerciseService {
	
	@Autowired
	ExerciseRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService service;

	public List<Exercise> getAllExercise() {
		return repo.findAll();
	}

	public Exercise getById(Long id) {
		return repo.findById(id).orElseThrow(()-> new ExerciseNotFoundException("exercise not found"));
	}

	public void save(ExerciseDto exerciseDto, Long userId) {
		Exercise exercise = new Exercise();
		exercise.setDescription(exerciseDto.getDescription());
		exercise.setName(exerciseDto.getName());
		exercise.setReps(exerciseDto.getReps());
		exercise.setSets(exerciseDto.getSets());
		User user = service.getById(userId);
		List<Exercise> exercises = user.getExerciseList();
		exercise.setUser(user);
		exercises.add(exercise);
		repo.save(exercise);
		userRepo.save(user);
	}

	public void update(ExerciseDto exerciseDto, Long id) {
		Exercise exercise = getById(id);
		exercise.setDescription(exerciseDto.getDescription());
		exercise.setName(exerciseDto.getName());
		exercise.setReps(exerciseDto.getReps());
		exercise.setSets(exerciseDto.getSets());
		repo.save(exercise);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
