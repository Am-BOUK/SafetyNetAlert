package safety.net.alerts.controller;

import static org.hamcrest.CoreMatchers.hasItem;
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
class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllMedicalRecordsTest() throws Exception {

		mockMvc.perform(get("/medicalRecords")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].birthdate", is("03/06/1984")));
	}

	@Test
	public void getMedicalRecordTest_whenMedicalRecordExists() throws Exception {
		mockMvc.perform(get("/medicalRecord?firstName=John&lastName=Boyd")).andExpect(status().isOk())
				.andExpect(jsonPath("$.birthdate", is("03/06/1984")));
	}

	@Test
	public void getMedicalRecordTest_whenMedicalRecordDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/medicalRecord?firstName=toto&lastName=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getMedicalRecord_whenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/medicalRecord?firstName=John")).andExpect(status().isConflict());
	}

	@Test
	public void getListMedicalRecordByFirstNameAndLastNameTest_whenMedicalRecordExists() throws Exception {
		mockMvc.perform(get("/medicalRecordsList?firstName=Jo&lastName=Boy")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].birthdate", is("03/06/1984")));
	}

	@Test
	public void getListMedicalRecordByFirstNameAndLastNameTest_whenMedicalRecordDosNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/medicalRecordsList?firstName=toto&lastName=Boy")).andExpect(status().isConflict());
	}

	@Test
	public void getListMedicalRecordByFirstNameAndLastNameTest_whenwhenNullRequestParam_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/medicalRecordsList?firstName=John")).andExpect(status().isConflict());
	}

	@Test
	public void addMedicalRecordTest_whenMedicalRecordDoesNotExist_shouldReturnOK() throws Exception {
		String medicalRecord = "{\"firstName\": \"Rostow\",\"lastName\": \"Gokeng\", \"birthdate\":\"03/06/1984\", \"medications\":[\"Andol:500mg\"], \"allergies\":[\"chat\"]}";
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord").content(medicalRecord)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void addMedicalRecordTest_whenMedicalRecordAlreadyExist_shouldReturnConflict() throws Exception {
		String medicalRecord = "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"]}";
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord").content(medicalRecord)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void addMedicalRecordTest_whenNullValue_thenReturnBadRequest() throws Exception {
		String medicalRecord = "{\"firstName\": \"test\",\"lastName\": \"test\", \"medications\":[\"Andol:500mg\"], \"allergies\":[\"chat\"]}";
		;
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord").content(medicalRecord)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void updateMedicalRecordTest_whenMedicalRecordDoesNotExist_shouldReturnConflict() throws Exception {
		String medicalRecord = "{\"medications\":[\"test:500mg\"]}";
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord?firstName=toto&lastName=Gokeng")
				.content(medicalRecord).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void updateMedicalRecordTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		String medicalRecord = "{\"medications\":[\"test:500mg\"]}";
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord?lastName=Gokeng").content(medicalRecord)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void updateMedicalRecordTest_whenMedicalRecordExists_shouldReturnOK() throws Exception {

		String medicalRecord = "{\"firstName\": \"Rostow\",\"lastName\": \"Rostow\", \"birthdate\":\"03/06/1984\", \"medications\":[\"Andol:500mg\"], \"allergies\":[\"chat\"]}";
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord").content(medicalRecord)
				.contentType(MediaType.APPLICATION_JSON));

		String medicalRecordDTO = "{\"medications\":[\"test:500mg\"]}";
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord?firstName=Rostow&lastName=Rostow")
				.content(medicalRecordDTO).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.birthdate", is("03/06/1984")))
				.andExpect(jsonPath("$.medications", hasItem("test:500mg")));

		mockMvc.perform(delete("/medicalRecord?firstName=Rostow&lastName=Rostow"));

	}

	@Test
	public void deleteMedicalRecordTest_whenMedicalRecordExists_shouldReturnOK() throws Exception {
		mockMvc.perform(delete("/medicalRecord?firstName=Rostow&lastName=Gokeng")).andExpect(status().isOk());
	}

	@Test
	public void deleteMedicalRecordTest_whenBadURI_shouldReturnMethodNotAllowed() throws Exception {
		mockMvc.perform(delete("/medicalRecordsList?firstName=Rostow&lastName=Gokeng"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void deleteMedicalRecordTest_whenMedicalRecordDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/medicalRecord?firstName=toto&lastName=toto")).andExpect(status().isConflict());
	}

	@Test
	public void deleteMedicalRecordTest_whenNullRequestParam_shouldReturnConflict() throws Exception {
		mockMvc.perform(delete("/medicalRecord?firstName=toto")).andExpect(status().isConflict());
	}

}
