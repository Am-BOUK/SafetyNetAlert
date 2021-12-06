package safety.net.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk()).andExpect(content().string(
				"{\"Person count \":[\"Adult count 5\",\"Child count 1\"],\"Station number :1\":[\"First name Peter\",\"Last name Duncan\",\"Adress 644 Gershwin Cir\",\"Phone number 841-874-6512\",\"First name Reginold\",\"Last name Walker\",\"Adress 908 73rd St\",\"Phone number 841-874-8547\",\"First name Jamie\",\"Last name Peters\",\"Adress 908 73rd St\",\"Phone number 841-874-7462\",\"First name Brian\",\"Last name Stelzer\",\"Adress 947 E. Rose Dr\",\"Phone number 841-874-7784\",\"First name Shawna\",\"Last name Stelzer\",\"Adress 947 E. Rose Dr\",\"Phone number 841-874-7784\",\"First name Kendrik\",\"Last name Stelzer\",\"Adress 947 E. Rose Dr\",\"Phone number 841-874-7784\"]}"));
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
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk()).andExpect(content()
				.string("{\"Roger Boyd 4 ans\":[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6513\",\"email\":\"drk@email.com\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6544\",\"email\":\"jaboyd@email.com\"}],\"Tenley Boyd 9 ans\":[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6513\",\"email\":\"drk@email.com\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6544\",\"email\":\"jaboyd@email.com\"}]}"));
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
		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk()).andExpect(content().string(
				"{\"List of phone numbers of the residents served by the fire station : 1\":[\"841-874-6512\",\"841-874-8547\",\"841-874-7462\",\"841-874-7784\",\"841-874-7784\",\"841-874-7784\"]}"));
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
		mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk()).andExpect(content().string(
				"{\"Address : 1509 Culver St, Sation Number :3\":[\"First name : John\",\"Last name : Boyd\",\"Medications : [aznol:350mg, hydrapermazol:100mg]\",\"Allergies : [nillacilan]\",\"Phone number : 841-874-6512\",\"Age : 37\",\"First name : Jacob\",\"Last name : Boyd\",\"Medications : [pharmacol:5000mg, terazine:10mg, noznazol:250mg]\",\"Allergies : []\",\"Phone number : 841-874-6513\",\"Age : 32\",\"First name : Tenley\",\"Last name : Boyd\",\"Medications : []\",\"Allergies : [peanut]\",\"Phone number : 841-874-6512\",\"Age : 9\",\"First name : Roger\",\"Last name : Boyd\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-6512\",\"Age : 4\",\"First name : Felicia\",\"Last name : Boyd\",\"Medications : [tetracyclaz:650mg]\",\"Allergies : [xilliathal]\",\"Phone number : 841-874-6544\",\"Age : 35\"]}"));
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

		mockMvc.perform(get("/flood/stations?stations=1,2")).andExpect(status().isOk()).andExpect(content().string(
				"{\"Station number : 1, address : 947 E. Rose Dr\":[\"First name : Brian\",\"Last name : Stelzer\",\"Medications : [ibupurin:200mg, hydrapermazol:400mg]\",\"Allergies : [nillacilan]\",\"Phone number : 841-874-7784\",\"Age : 46\",\"First name : Shawna\",\"Last name : Stelzer\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-7784\",\"Age : 41\",\"First name : Kendrik\",\"Last name : Stelzer\",\"Medications : [noxidian:100mg, pharmacol:2500mg]\",\"Allergies : []\",\"Phone number : 841-874-7784\",\"Age : 7\"],\"Station number : 2, address : 892 Downing Ct\":[\"First name : Sophia\",\"Last name : Zemicks\",\"Medications : [aznol:60mg, hydrapermazol:900mg, pharmacol:5000mg, terazine:500mg]\",\"Allergies : [peanut, shellfish, aznol]\",\"Phone number : 841-874-7878\",\"Age : 33\",\"First name : Warren\",\"Last name : Zemicks\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-7512\",\"Age : 36\",\"First name : Zach\",\"Last name : Zemicks\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-7512\",\"Age : 4\"],\"Station number : 2, address : 951 LoneTree Rd\":[\"First name : Eric\",\"Last name : Cadigan\",\"Medications : [tradoxidine:400mg]\",\"Allergies : []\",\"Phone number : 841-874-7458\",\"Age : 76\"],\"Station number : 1, address : 644 Gershwin Cir\":[\"First name : Peter\",\"Last name : Duncan\",\"Medications : []\",\"Allergies : [shellfish]\",\"Phone number : 841-874-6512\",\"Age : 21\"],\"Station number : 2, address : 29 15th St\":[\"First name : Jonanathan\",\"Last name : Marrack\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-6513\",\"Age : 32\"],\"Station number : 1, address : 908 73rd St\":[\"First name : Reginold\",\"Last name : Walker\",\"Medications : [thradox:700mg]\",\"Allergies : [illisoxian]\",\"Phone number : 841-874-8547\",\"Age : 42\",\"First name : Jamie\",\"Last name : Peters\",\"Medications : []\",\"Allergies : []\",\"Phone number : 841-874-7462\",\"Age : 39\"]}"));
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
	public void getAllPersonsByListOfStationNumberTest_whenNumberStationNull_shouldReturnConflict() throws Exception {
		mockMvc.perform(get("/flood/stations?stations")).andExpect(status().isConflict());
	}

//	6
	@Test
	public void getPersonsInfoByFirstNameAndLastNameTest_whenPersonExists_shouldReturnOK() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=john&lastName=boyd")).andExpect(status().isOk()).andExpect(content()
				.string("{\"John Boyd\":[\"Address : 1509 Culver St\",\"Age : 37\",\"Email address : jaboyd@email.com\",\"Medications : [aznol:350mg, hydrapermazol:100mg]\",\"Allergies : [nillacilan]\"]}"));
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
		mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk()).andExpect(content().string(
				"{\"List of e-mail addresses of all the inhabitants of the city : Culver\":[\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"tcoop@ymail.com\",\"lily@email.com\",\"soph@email.com\",\"ward@email.com\",\"zarc@email.com\",\"reg@email.com\",\"jpeter@email.com\",\"jpeter@email.com\",\"aly@imail.com\",\"bstel@email.com\",\"ssanw@email.com\",\"bstel@email.com\",\"clivfd@ymail.com\",\"gramps@email.com\"]}"));
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
