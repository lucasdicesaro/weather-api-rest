package weather.repositories;

import org.springframework.data.repository.CrudRepository;

import weather.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String userName);

}