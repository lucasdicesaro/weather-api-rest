package weather.repositories;

import org.springframework.data.repository.CrudRepository;

import weather.model.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

	  Location findByLocationName(String locationName);

}