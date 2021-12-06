package safety.net.alerts.controller.IT;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import safety.net.alerts.controller.SafetyNetAlertRestService;

@SpringBootTest
public class SafetyNetAlertControllerIT {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertMetierImpl");

	@Autowired
	private SafetyNetAlertRestService safetyNetAlertRestService;

	@Test
	public void testGetAllPersonsByStationNumber_whenStationNumberExists() throws Exception {
		assertNotNull(this.safetyNetAlertRestService.getAllPersonsByStationNumber(1));
	}

	@Test
	public void testGetAllPersonsByStationNumber_whenStationNumberDoesNotExist() {
		try {
			safetyNetAlertRestService.getAllPersonsByStationNumber(100);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The fire station number : 100 , does not exist !"));
		}
	}

	@Test
	public void testGetAllPersonsByStationNumber_whenStationNumberNull() {
		try {
			safetyNetAlertRestService.getAllPersonsByStationNumber(0);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The fire station number : 100 , does not exist !"));
		}
	}

	@Test
	public void testGetchildrendAndFamilyMemberByAddress_whenAddressExists() throws Exception {
		assertNotNull(safetyNetAlertRestService.getchildrendAndFamilyMemberByAddress("1509 Culver St"));

	}

	@Test
	public void testGetchildrendAndFamilyMemberByAddress_whenAddressDoesNotExists() {
		try {
			safetyNetAlertRestService.getchildrendAndFamilyMemberByAddress("toto");
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("No child in address :toto"));
		}
	}

	@Test
	public void testGetchildrendAndFamilyMemberByAddress_whenAddressNull() {
		try {
			safetyNetAlertRestService.getchildrendAndFamilyMemberByAddress(null);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("No child in address :null"));
		}
	}

	@Test
	public void testGetAllPhoneNumbersByFireStationNumber_whenStationNumberExists() throws Exception {
		assertNotNull(safetyNetAlertRestService.getAllPhoneNumbersByFireStationNumber(1));
	}

	@Test
	public void testGetAllPhoneNumbersByFireStationNumber_whenStationNumberDoesNotExist() {
		try {
			safetyNetAlertRestService.getAllPhoneNumbersByFireStationNumber(100);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The fire station number : 100 , does not exist !"));
		}
	}

	@Test
	public void testGetAllPersonsAndStationNumberAndMedicalRecordByAddress_whenAddressExists() throws Exception {
		assertNotNull(
				safetyNetAlertRestService.getAllPersonsAndStationNumberAndMedicalRecordByAddress("1509 Culver St"));
	}

	@Test
	public void testGetAllPersonsAndStationNumberAndMedicalRecordByAddress_whenAddressDoesNotExist() {
		try {
			safetyNetAlertRestService.getAllPersonsAndStationNumberAndMedicalRecordByAddress("toto");
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The address : toto , does not exist !"));
		}
	}

	@Test
	public void testGetAllPersonsByListOfStationNumber_whenNumberStationExists() throws Exception {
		int[] numberStation = { 1, 2 };
		assertNotNull(safetyNetAlertRestService.getAllPersonsByListOfStationNumber(numberStation));
	}

	@Test
	public void testGetPersonsInfoByFirstNameAndLastNameTest_whenPersonExists() throws Exception {
		assertNotNull(safetyNetAlertRestService.getPersonsInfoByFirstNameAndLastName("John", "Boyd"));
	}

	@Test
	public void testGetPersonsInfoByFirstNameAndLastNameTest_whenPersonDoesNotExist() {
		try {
			safetyNetAlertRestService.getPersonsInfoByFirstNameAndLastName("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The list of person toto toto, you want to get, is empty !"));
			logger.info(e.getMessage());
		}
	}

	@Test
	public void testAllEmailsByCityTest_whenCityExists() throws Exception {
		assertNotNull(safetyNetAlertRestService.getAllEmailsByCity("Culver"));
	}

	@Test
	public void testAllEmailsByCityTest_whenCityDoesNotExist() {
		try {
			safetyNetAlertRestService.getAllEmailsByCity("toto");
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The city : toto , does not exist !"));
		}

	}

	@Test
	public void testAllEmailsByCityTest_whenCityNull() {
		try {
			safetyNetAlertRestService.getAllEmailsByCity(null);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertTrue(e.getMessage().contains("The city : null , does not exist !"));
		}

	}

}
