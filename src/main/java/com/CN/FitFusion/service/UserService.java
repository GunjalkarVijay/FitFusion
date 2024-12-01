package com.CN.FitFusion.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.Role;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
	}

	public void updateById(UserDto userDto, Long id) {
		User user = getById(id);
		user.setAge(userDto.getAge());
		user.setContactNo(userDto.getContactNo());
		user.setEmail(userDto.getEmail());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		Set<Role> roles = user.getRoles();
		Role newRole = new Role();
		newRole.setRoleName(userDto.getUserType());
		roles.add(newRole);
		user.setRoles(roles);
		userRepository.save(user);
		
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<Exercise> getExerciseById(Long id) {
		User user = getById(id);
		return user.getExerciseList();
	}

	public List<Diet> getDietById(Long id) {
		User user = getById(id);
		return user.getDiets();
	}

	public void createUser(UserDto userRequest) {
        // 1. Encrypting the password for the user
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 2. Saving the encodedPassword in a string
        String encodedPassword = encoder.encode(userRequest.getPassword());
        // 3. Mapping the dto with the User entity so that we can save the user
        User user = User.builder().email(userRequest.getEmail()).age(userRequest.getAge())
                .gender(userRequest.getGender()).password(encodedPassword)
                .build();
        // 4. Creating new Role object and setting the role for the user from the userRequestDTO
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if(userRequest.getUserType() != null) {
            if (userRequest.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
                roles.add(role);
                user.setRoles(roles);
            } else if (userRequest.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
                roles.add(role);
                user.setRoles(roles);
            } else {
                role.setRoleName("ROLE_CUSTOMER");
                roles.add(role);
                user.setRoles(roles);
            }
        }
        else {
            role.setRoleName("ROLE_CUSTOMER");
            roles.add(role);
            user.setRoles(roles);
        }
        // 5. Finally saving the updated user to the repository
        userRepository.save(user);
    }

}
