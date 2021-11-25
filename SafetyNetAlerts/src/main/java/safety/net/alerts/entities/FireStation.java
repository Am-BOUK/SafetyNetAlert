package safety.net.alerts.entities;

import javax.validation.constraints.NotBlank;

/**
 * 
 * implementation of business object: fire station that will be manipulated by
 * the other layers.
 *
 * the fire station object has two attributes : address and station
 */
public class FireStation {

	@NotBlank(message = "Station is mandatory")
	private String station;

	@NotBlank(message = "Address is mandatory")
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

	@Override
	public String toString() {
		return "[address = " + address + ", station = " + station + "]";
	}

}
