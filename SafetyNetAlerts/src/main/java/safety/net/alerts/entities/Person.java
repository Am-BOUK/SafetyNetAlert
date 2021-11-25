package safety.net.alerts.entities;

import javax.validation.constraints.NotBlank;

/**
 * 
 * implementation of business object: person that will be manipulated by the
 * other layers.
 *
 * the person data has seven attribute : first name, last name, address, city,
 * zip, phone and email
 */
public class Person {
	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@NotBlank(message = "Address is mandatory")
	private String address;

	@NotBlank(message = "City is mandatory")
	private String city;

	@NotBlank(message = "Zip is mandatory")
	private String zip;

	@NotBlank(message = "Phone is mandatory")
	private String phone;

	@NotBlank(message = "Email is mandatory")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	@Override
	public String toString() {
		return " [ firstName = " + firstName + ", lastName = " + lastName + ", address = " + address + ", city = "
				+ city + ", zip = " + zip + ", phone = " + phone + ", email = " + email + "]";
	}

}
