package com.kpa.ems.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kpa.ems.entity.Role;
import com.kpa.ems.entity.UserEntity;
import com.kpa.ems.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		UserEntity user = userRepository.getUserByEmailId(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getUserEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))  // Add "ROLE_" prefix
            .collect(Collectors.toList());
    }
}
