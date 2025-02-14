package com.jacagi.rockpaperscissors.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jacagi.rockpaperscissors.api.entity.UserEntity;
import com.jacagi.rockpaperscissors.api.repository.UserRepository;
import com.jacagi.rockpaperscissors.model.service.UserInfoDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<UserEntity> user = this.userRepository.findByUsername(username);
      return user.isPresent()?new UserInfoDetails(user.get()): null;
    }
	
}
