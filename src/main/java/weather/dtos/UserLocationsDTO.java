package weather.dtos;

import java.util.List;

public class UserLocationsDTO {
	private Integer userId;
	private List<String> locationNames;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<String> getLocationNames() {
		return locationNames;
	}

	public void setLocationNames(List<String> locationNames) {
		this.locationNames = locationNames;
	}
}
