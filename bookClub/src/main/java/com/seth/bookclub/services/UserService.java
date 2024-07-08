package com.seth.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.seth.bookclub.models.LoginUser;
import com.seth.bookclub.models.User;
import com.seth.bookclub.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User register(User newUser, BindingResult result) {
		// Reject if email is taken (present in database)
		Optional<User> testUser = userRepo.findByEmail(newUser.getEmail());
		if (testUser.isPresent()) {
			result.rejectValue("email", "unique", "This email already exists!");
		}
		// Reject if password doesn't match confirmation
		String passwordEntered = newUser.getPassword();
		if (!passwordEntered.equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		}
		// Return null if result has errors
		if (result.hasErrors()) {
			return null;
		}
		// Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);

		return userRepo.save(newUser);
	}

	public User login(LoginUser newLogin, BindingResult result) {
		// Reject if email is not present in database
		Optional<User> testLog = userRepo.findByEmail(newLogin.getEmail());
		if (!testLog.isPresent()) {
			result.rejectValue("password", "invalid", "Invalid Email/Password");
			return null;
		}
		// Create a user object if present and verify password
		User loggedUser = testLog.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), loggedUser.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Email/Password");
			return null;
		}
		// If passed login validation return our logged in User object
		return loggedUser;
	}

}