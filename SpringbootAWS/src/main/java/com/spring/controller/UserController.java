package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entities.User;
import com.spring.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping
	public List<User> getAllUsers()
	{
		return this.repo.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long userId)
	{
		return this.repo.getById(userId);
				//.orElseThrow(() -> new ResourceNotFoundException
				//("User not found with id" + userID));
	}
	
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		return this.repo.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") Long userId, @RequestBody User user)
	{
		User existing = this.repo.getById(userId);
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return this.repo.save(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId)
	{
		//Optional<User> existingUser = this.repo.findById(userId);
		this.repo.deleteById(userId);
		return ResponseEntity.ok().build();
	}

}
