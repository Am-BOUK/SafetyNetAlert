package safety.net.alerts.entities;

/**
 * 
 * implementation of business data transfer object: person that will be
 * manipulated by the other layers.
 *
 * the person data transfer object has five attribute : address, city, zip,
 * phone and email
 */
public class PersonDTO {
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
