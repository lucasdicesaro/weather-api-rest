package weather.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import weather.model.Location;
import weather.model.User;
import weather.repositories.LocationRepository;
import weather.repositories.UserRepository;

@RestController
@RequestMapping(path="/locations")
public class LocationController {

	@Autowired 
	private LocationRepository locationRepository;
	@Autowired 
	private UserRepository userRepository;

	@GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<String> getUserLocations(@PathVariable("userId") Integer userId) throws Exception {
		Optional<User> optional = userRepository.findById(userId);
		User user = optional.orElseThrow(() -> 
			new Exception("getUserLocations.UserId '" + userId + "' doesn't exist")
		);		
		List<String> locationNames = user.getLocations().stream().map(Location::getLocationName).collect(Collectors.toList());
		
//		UserLocationsDTO userLocationsDTO = new UserLocationsDTO();
//		userLocationsDTO.setUserId(userId);
//		userLocationsDTO.setLocationNames(locationNames);
		return locationNames;
	}
	
	@PostMapping(path = "/add") 
	public @ResponseBody String addNewDashboard(@RequestParam String locationName, @RequestParam Integer userId) throws Exception {
		Optional<User> optional = userRepository.findById(userId);
		optional.ifPresent(user -> {
			Location location = locationRepository.findByLocationName(locationName);
			if (location == null) {
				location = new Location();
				location.setUser(user);
				location.setLocationName(locationName);
				locationRepository.save(location);
			} else {
				// TODO use custom exception and a wrapper method 
				throw new RuntimeException("addNewDashboard.locationName '" + locationName + "' already exists");
			}
		});
		optional.orElseThrow(() -> 
			new Exception("UserId '" + userId + "' doesn't exist")
		);
		
		return "Saved";
	}


	@PostMapping(path = "/remove") 
	public @ResponseBody String addRemoveDashboard(@RequestParam String locationName, @RequestParam Integer userId) throws Exception {
		Optional<User> optionalUser = userRepository.findById(userId);
		optionalUser.ifPresent(user -> {
			Location location = locationRepository.findByLocationName(locationName);
			if (location != null) {
				locationRepository.delete(location);
			} else {
				// TODO use custom exception and a wrapper method 
				throw new RuntimeException("addRemoveDashboard.locationName '" + locationName + "' doesn't exist");
			}
		});
		optionalUser.orElseThrow(() -> 
			new Exception("UserId '" + userId + "' doesn't exist")
		);
		
		return "Saved";
	}
}
