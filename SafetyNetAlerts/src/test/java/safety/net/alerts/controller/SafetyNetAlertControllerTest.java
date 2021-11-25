package safety.net.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetAlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllPersonsByStationNumber_whenStationNumberExists_shouldReturnOK() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
	}

	@Test
	public void getAllPersonsByStationNumber_whenStationNumberDoesNotExist_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=116")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByStationNumber_whenStationNumberNotInteger_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByStationNumber_whenStationNumberNull_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber")).andExpect(status().isConflict());
	}
//
	@Test
	public void getchildrendAndFamilyMemberByAddress_whenAddressExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
				//.andExpect(jsonPath("$", hasItem("Boyd")));  //@ revoir
	}
	
	@Test
	public void getchildrendAndFamilyMemberByAddress_whenAddressDoesNotExists_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/childAlert?address=toto")).andExpect(status().isConflict());
	}
	
	@Test
	public void getchildrendAndFamilyMemberByAddress_whenAddressNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/childAlert?address")).andExpect(status().isConflict());
	}
//
	@Test
	public void getAllPersonsByAddressOfStationNumber_whenNumberStationExists_shouldReturnOK() throws Exception {

		mockMvc.perform(get("/flood/stations?station=1")).andExpect(status().isOk());
	}

	@Test
	public void getAllPersonsByAddressOfStationNumber_whenNumberStationNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/flood/stations?station=166")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByAddressOfStationNumber_whenNumberStationNotInteger_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/flood/stations?station=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByAddressOfStationNumber_whenNumberStationNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/flood/stations?station")).andExpect(status().isConflict());
	}

}
