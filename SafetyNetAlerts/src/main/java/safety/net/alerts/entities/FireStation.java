package safety.net.alerts.entities;

public class FireStation {
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

	@Override
	public String toString() {
		return "[address = " + address + ", station = " + station + "]";
	}

}