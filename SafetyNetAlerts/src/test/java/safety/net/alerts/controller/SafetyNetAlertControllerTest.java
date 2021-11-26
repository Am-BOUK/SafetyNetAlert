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

//1
	@Test
	public void getAllPersonsByStationNumberTest_whenStationNumberExists_shouldReturnOK() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
	}

	@Test
	public void getAllPersonsByStationNumberTest_whenStationNumberDoesNotExist_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=116")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByStationNumberTest_whenStationNumberNotInteger_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByStationNumberTest_whenStationNumberNull_shouldReturnConflict() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber")).andExpect(status().isConflict());
	}

//2
	@Test
	public void getchildrendAndFamilyMemberByAddressTest_whenAddressExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
		// .andExpect(jsonPath("$", hasItem("Boyd"))); //@ revoir
	}

	@Test
	public void getchildrendAndFamilyMemberByAddressTest_whenAddressDoesNotExists_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/childAlert?address=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getchildrendAndFamilyMemberByAddressTest_whenAddressNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/childAlert?address")).andExpect(status().isConflict());
	}

//3
	@Test
	public void getAllPhoneNumbersByFireStationNumberTest_whenStationNumberExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());
	}

	@Test
	public void getAllPhoneNumbersByFireStationNumberTest_whenStationNumberDoesNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=100")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPhoneNumbersByFireStationNumberTest_whenStationNumberNotInteger_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPhoneNumbersByFireStationNumberTest_whenStationNumberNull_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation")).andExpect(status().isConflict());
	}

//	4 
	@Test
	public void getAllPersonsAndStationNumberAndMedicalRecordByAddressTest_whenAddressExists_shouldReturnOK()
			throws Exception {
		mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk());
	}

	@Test
	public void getAllPersonsAndStationNumberAndMedicalRecordByAddressTest_whenAddressDoesNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/fire?address=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsAndStationNumberAndMedicalRecordByAddressTest_whenAddressNull_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/fire?address")).andExpect(status().isConflict());
	}

//	5
	@Test
	public void getAllPersonsByListOfStationNumberTest_whenNumberStationExists_shouldReturnOK() throws Exception {

		mockMvc.perform(get("/flood/stations?stations=1,2")).andExpect(status().isOk());
	}

	@Test
	public void getAllPersonsByListOfStationNumberTest_whenNumberStationNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/flood/stations?stations=166")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByListOfStationNumberTest_whenNumberStationNotInteger_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/flood/stations?stations=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllPersonsByListOfStationNumberTest_whenNumberStationNull_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/flood/stations?stations")).andExpect(status().isConflict());
	}

//	6
	@Test
	public void getPersonsInfoByFirstNameAndLastNameTest_whenPersonExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=john&lastName=boyd")).andExpect(status().isOk());
	}

	@Test
	public void getPersonsInfoByFirstNameAndLastNameTest_whenPersonDoesNotExist_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/personInfo?firstName=toto&lastName=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getPersonsInfoByFirstNameAndLastNameTest_whenOneOfRequestParamNull_shouldReturnConflict()
			throws Exception {
		mockMvc.perform(get("/personInfo?firstName=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getPersonsInfoByFirstNameAndLastNameTest_whenRequestParamNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/personInfo")).andExpect(status().isConflict());
	}

//	7
	@Test
	public void getAllEmailsByCityTest_whenCityExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk());
	}

	@Test
	public void getAllEmailsByCityTest_whenCityDoesNotExist_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/communityEmail?city=toto")).andExpect(status().isConflict());
	}

	@Test
	public void getAllEmailsByCityTest_whenCityNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/communityEmail?city")).andExpect(status().isConflict());
	}
}
