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
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllPersonsTest() throws Exception {

		mockMvc.perform(get("/persons")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is("John")));
	}

	@Test
	public void getPersonTest_whenPersonExists() throws Exception {
		mockMvc.perform(get("/person?firstName=John&lastName=Boyd")).andExpect(status().isOk());
	}

	@Test
	public void getPersonTest_whenPersonDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/person?firstName=test&lastName=test")).andExpect(status().isConflict());
	}

	@Test
	public void getPerson_whenwhenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/person?firstName=John")).andExpect(status().isConflict());
	}

	@Test
	public void getListPersonByFirstNameAndLastNameTest_whenPersonExists() throws Exception {
		mockMvc.perform(get("/personsList?firstName=Jo&lastName=Boy")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Test
	public void getListPersonByFirstNameAndLastNameTest_whenPersonDosNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/personsList?firstName=toto&lastName=Boy")).andExpect(status().isConflict());
	}

	@Test
	public void getListPersonByFirstNameAndLastNameTest_whenwhenNullRequestParam_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/personsList?firstName=John")).andExpect(status().isConflict());
	}

	@Test
	public void addPersonTest_whenPersonDoesNotExist_shouldReturnOK() throws Exception {
		String person = "{\"firstName\": \"Rostow\",\"lastName\": \"Gokeng\",\"address\":\"123 rue de paris\",\"zip\" : \"20000\", \"city\":\"Paris\",\"phone\":\"11111111\",\"email\" : \"rostow@domain.com\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/person").content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addPersonTest_whenPersonAlreadyExist_shouldReturnConflict() throws Exception {
		String person = "{\"firstName\": \"John\",\"lastName\": \"Boyd\",\"address\":\"123 rue de paris\",\"zip\" : \"20000\", \"city\":\"Paris\",\"phone\":\"11111111\",\"email\" : \"rostow@domain.com\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/person").content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}

	@Test
	public void addPersonTest_whenNullValue_thenReturnBadRequest() throws Exception {
		String person = "{\"firstName\": \"test\",\"address\":\"123 rue de paris\",\"zip\" : \"20000\"}";
		;
		mockMvc.perform(MockMvcRequestBuilders.post("/person").content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updatePersonTest_whenPersonExists_shouldReturnOK() throws Exception {
		String person = "{\"firstName\": \"Rostow\",\"lastName\": \"Gokeng\",\"address\":\"modified\",\"zip\" : \"modified\", \"city\":\"Paris\",\"phone\":\"11111111\",\"email\" : \"rostow@domain.com\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/person?firstName=Rostow&lastName=Gokeng").content(person)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address", is("modified"))).andExpect(jsonPath("$.phone", is("11111111")));
	}

	@Test
	public void updatePersonTest_whenPersonDoesNotExist_shouldReturnConflict() throws Exception {
		String person = "{\"address\":\"test\",\"zip\" : \"test\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/person?firstName=toto&lastName=Gokeng").content(person)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void updatePersonTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		String person = "{\"address\":\"modified\",\"zip\" : \"test\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/person?lastName=Gokeng").content(person)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void deletePersonTest_whenPersonExists_shouldReturnOK() throws Exception {
		mockMvc.perform(delete("/person?firstName=Rostow&lastName=Gokeng")).andExpect(status().isOk());
	}

	@Test
	public void deletePersonTest_whenBadURI_shouldReturnMethodNotAllowed() throws Exception {
		mockMvc.perform(delete("/personsList?firstName=Rostow&lastName=Gokeng"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void deletePersonTest_whenPersonDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/person?firstName=toto&lastName=toto")).andExpect(status().isConflict());
	}

	@Test
	public void deletePersonTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/person?firstName=toto")).andExpect(status().isConflict());
	}

}
