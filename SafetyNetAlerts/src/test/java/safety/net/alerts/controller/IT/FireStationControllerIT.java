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
import safety.net.alerts.controller.FireStationRestService;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;

@SpringBootTest(classes = SafetyNetAlertsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class FireStationControllerIT {

	@Autowired
	private FireStationRestService fireStationRestService;

	HttpHeaders headers = new HttpHeaders();

	FireStation fireStation = new FireStation();
	FireStationDTO fireStationDTO = new FireStationDTO();

	@Test
	public void testGetAllFireStations() throws Exception {
		assertNotNull(fireStationRestService.getAllFireStations());
	}

	@Test
	public void testGetFireStation_whenAddressExists() throws Exception {
		assertEquals(fireStationRestService.getFireStation("1509 Culver St").getStation(), "3");
	}

	@Test
	public void testGetFireStation_whenAddressDoesNotExist() throws Exception {
		try {
			fireStationRestService.getFireStation("toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(
					e.getMessage().contains("The fire station of the address toto, you want to get, does not exist !"));
		}

	}

	@Test
	public void testGetListFireStationByAddress_whenAddressExists() throws Exception {
		assertFalse(fireStationRestService.getListFireStationByAddress("st").isEmpty());
	}
	
	@Test
	public void testGetListFireStation_whenMFireStationDoesNotExist() throws Exception {
		try {
			fireStationRestService.getListFireStationByAddress("toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The fire station of the address toto, you want to get, is empty !"));
		}
	}

	@Test
	public void testAddFireStation_whenAddressDoesNotExist() throws Exception {
		fireStation.setAddress("integration");
		fireStation.setStation("test");

		assertEquals(fireStationRestService.addFireStation(fireStation).getAddress(), "integration");

	}

	@Test
	public void testAddFireStation_whenAddressExists() throws Exception {

		try {
			testAddFireStation_whenAddressDoesNotExist();

			// THEN
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains(
					"the fire station you want to add : [address = integration, station = test], Already exist !"));
		}
	}

	@Test
	public void testUpdateFireStation_whenAddressExists() throws Exception {
		fireStationDTO.setStation("modified");
		assertEquals(this.fireStationRestService.updateFireStation("integration", fireStationDTO).getStation(),
				"modified");
	}

	@Test
	public void testUpdateFireStation_whenAddressDoesNotExists() throws Exception {
		fireStationDTO.setStation("modified");
		try {
			fireStationRestService.updateFireStation("toto", fireStationDTO);
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(
					e.getMessage().contains("Fire station of the address : toto you want to update, does not exist !"));
		}
	}

	@Test
	public void testDeleteFireStation_whenAddressExists() throws Exception {
		fireStationRestService.deleteFireStation("integration");
		Assertions.assertThat(fireStation.getAddress()).isNull();
	}

	@Test
	public void testDeleteFireStation_whenAddressDosNotExist() throws Exception {
		try {
			fireStationRestService.deleteFireStation("toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(
					e.getMessage().contains("Fire station of the address : toto you want to delete, does not exist !"));
		}
	}

}
