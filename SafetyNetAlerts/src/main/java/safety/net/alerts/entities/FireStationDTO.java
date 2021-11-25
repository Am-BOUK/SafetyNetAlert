package safety.net.alerts.entities;
/**
 * 
 * implementation of business data transfer object: fire station that will be manipulated by
 * the other layers.
 *
 * the fire station data transfer object has two attributes : address and station
 */
public class FireStationDTO {
	private String station;
	private String address;

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
