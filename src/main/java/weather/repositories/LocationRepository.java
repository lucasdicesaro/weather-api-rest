package weather.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import weather.model.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

	Location findByLocationName(String locationName);

	@Query("SELECT l FROM Location l where l.locationName = :locationName AND l.user.id = :userId")
	Location findByLocationNameAndUserId(@Param("locationName") String title, @Param("userId") Integer userId);

}