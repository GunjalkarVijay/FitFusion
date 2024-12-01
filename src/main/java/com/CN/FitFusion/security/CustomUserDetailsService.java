package com.CN.FitFusion.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}



}
