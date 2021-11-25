package safety.net.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllFireStationsTest() throws Exception {

		mockMvc.perform(get("/fireStations")).andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("3")));
	}

	@Test
	public void getFireStationTest() throws Exception {
		mockMvc.perform(get("/fireStation?address=1509 Culver St")).andExpect(status().isOk());
	}

	@Test
	public void getFireStation_whenAddressDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/fireStation?address=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getFireStation_whenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/fireStation?")).andExpect(status().isConflict());
	}

	@Test
	public void getListFireStationByAddressTest() throws Exception {
		mockMvc.perform(get("/fireStationsList?address=Culver")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].station", is("3"))).andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Test
	public void getListFireStationByAddressTest_whenAddressDosNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/fireStationsList?address=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getListFireStationByAddressTest_whenwhenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/fireStationsList")).andExpect(status().isConflict());
	}

	@Test
	public void addFireStationTest_whenAddressDoesNotExist_shouldReturnOK() throws Exception {
		String fireStation = "{\"address\": \"30 rue wissal\",\"station\": \"55\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/fireStation").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void addFireStationTest_whenAddressAlreadyExist_shouldReturnConflict() throws Exception {
		String fireStation = "{\"address\": \"1509 Culver St\",\"station\": \"3\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/fireStation").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void addFireStationTest_whenNullValue_thenReturnBadRequest() throws Exception {
		String fireStation = "{\"station\": \"55\"}";
		;
		mockMvc.perform(MockMvcRequestBuilders.post("/fireStation").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void updateFireStationTest_whenFireStationExists_shouldReturnOK() throws Exception {
		String fireStation = "{\"station\": \"605\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/fireStation?address=30 rue wissal").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.station", is("605")));
	}

	@Test
	public void updateFireStationTest_whenFireStationDoesNotExist_shouldReturnConflict() throws Exception {
		String fireStation = "{\"station\": \"605\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/fireStation?address=toto").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void updateFireStationTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		String fireStation = "{\"station\": \"605\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/fireStation?").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void updateFireStationTest_whenNullRequestBody_shouldReturnOk() throws Exception {
		String fireStation = "{}";
		;
		mockMvc.perform(MockMvcRequestBuilders.put("/fireStation?address=30 rue wissal").content(fireStation)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.station", is("605")));
	}

	@Test
	public void deleteFireStationTest_whenFireStationExists_shouldReturnOK() throws Exception {
		mockMvc.perform(delete("/fireStation?address=30 rue wissal")).andExpect(status().isOk());
	}

	@Test
	public void deleteFireStationTest_whenBadURI_shouldReturnNotFound() throws Exception {
		mockMvc.perform(delete("/fireStationList?address=30 rue wissal")).andExpect(status().isNotFound());
	}

	@Test
	public void deleteFireStationTest_whenFireStationDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/fireStation?address=toto")).andExpect(status().isConflict());
	}

	@Test
	public void deleteFireStationTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/fireStation?")).andExpect(status().isConflict());
	}

}
