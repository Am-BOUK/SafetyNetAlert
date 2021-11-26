package safety.net.alerts.controller.IT;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;

import safety.net.alerts.SafetyNetAlertsApplication;
import safety.net.alerts.controller.PersonRestService;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;

@SpringBootTest(classes = SafetyNetAlertsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

	@Autowired
	private PersonRestService personRestService;
	HttpHeaders headers = new HttpHeaders();

	Person person = new Person();
	PersonDTO personDTO = new PersonDTO();

	@Test
	public void testGetAllPersons() throws Exception {
		assertNotNull(this.personRestService.getAllPersons());
	}

	@Test
	public void testGetPerson_whenPersonExist() throws Exception {
		assertEquals(this.personRestService.getPerson("John", "Boyd").getAddress(), "1509 Culver St");
	}

	@Test
	public void testGetPerson_whenPersonDoesNotExist() throws Exception {
		try {
			personRestService.getPerson("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The person toto toto, you want to get, does not exist !"));
		}
	}

	@Test
	public void testGetListPersonByFirstNameAndLastName_whenPersonExist() throws Exception {
		assertFalse(this.personRestService.getListPersonByFirstNameAndLastName("Jo", "Boy").isEmpty());
	}

	@Test
	public void testAddPerson_whenPersonDoesNotExist() throws Exception {
		person.setFirstName("integration");
		person.setLastName("test");
		person.setAddress("testIntegration");
		person.setCity("integration");
		person.setZip("111111");
		person.setPhone("11111111");
		person.setEmail("test@integration.com");

		assertEquals(this.personRestService.addPerson(person).getAddress(), "testIntegration");

	}

	@Test
	public void testAddPerson_whenPersonExists() throws Exception {

		try {
			testAddPerson_whenPersonDoesNotExist();

			// THEN
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The person integration test you want to add, already exists !"));
		}
	}

	@Test
	public void testUpdatePerson_whenPersonExists() throws Exception {
		personDTO.setAddress("modified");
		personDTO.setEmail("test@modified.com");
		assertEquals(this.personRestService.updatePerson("integration", "test", personDTO).getAddress(), "modified");
	}

	@Test
	public void testUpdatePerson_whenPersonDoesNotExist() throws Exception {
		personDTO.setAddress("modified");
		try {
			personRestService.updatePerson("toto", "toto", personDTO);
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The person toto toto you want to update, does not exist !"));
		}
	}

	@Test
	public void testDeletePerson_whenPersonExists() throws Exception {
		personRestService.deletePersonByName("integration", "test");
		Assertions.assertThat(person.getFirstName()).isNull();

	}

	@Test
	public void testDeletePerson_whenPersonDosNotExist() throws Exception {
		try {
			personRestService.deletePersonByName("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The person toto toto you want to delete, does not exist !"));
		}
	}

}
