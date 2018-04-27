package weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import weather.model.User;
import weather.repositories.UserRepository;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userRepository.save(user);
		} else {
			// TODO use custom exception and a wrapper method 
			throw new RuntimeException("addNewUser.username '" + username + "' already exists");
		}
		
		return "Saved";
	}


	@GetMapping(path = "/retrieve")
	public @ResponseBody String retrieveUserId(@RequestParam String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			// TODO use custom exception and a wrapper method 
			throw new RuntimeException("retrieveUserId.username '" + username + "' doesn't exist");
		}
		
		return user.getId().toString();
	}
}
