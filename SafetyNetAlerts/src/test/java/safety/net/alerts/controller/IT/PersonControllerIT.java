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
	public void testGetPerson() throws Exception {
		assertEquals(this.personRestService.getPerson("John", "Boyd").getAddress(), "1509 Culver St");
	}

	@Test
	public void testGetListPersonByFirstNameAndLastName() throws Exception {
		assertFalse(this.personRestService.getListPersonByFirstNameAndLastName("Jo", "Boy").isEmpty());
	}

	@Test
	public void testAddPerson() throws Exception {
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
			testAddPerson();

			// THEN
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The person integration test you want to add, already exists !"));
		}
	}

	@Test
	public void testUpdatePerson() throws Exception {
		personDTO.setAddress("modified");
		personDTO.setEmail("test@modified.com");
		assertEquals(this.personRestService.updatePerson("integration", "test", personDTO).getAddress(), "modified");
	}

	@Test
	public void testDeletePerson() throws Exception {
		personRestService.deletePersonByName("integration", "test");
		Assertions.assertThat(person.getFirstName()).isNull();

	}

}
//	@MockBean
//	private IPersonRepository personRepository;
//
//	@Autowired
//	PersonRestService personRestService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	private Person mockPerson = new Person();
//
//
//	@BeforeEach
//	public void setUpPerTest() {
//		
//		mockPerson.setFirstName("Rostow");
//		mockPerson.setLastName("Gokeng");
//		mockPerson.setAddress("123 rue de paris");
//		mockPerson.setCity("P");
//		mockPerson.setZip("20000");
//		mockPerson.setPhone("11111111");
//		mockPerson.setEmail("ahlam@boukili.com");
//	}	
//	
//	@Test
//	public void whenPostRequestToPersonsAndValidPerson_thenCorrectResponse() throws Exception {
//	    MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
//	    String person = "{\"firstName\": \"Rostow\",\"lastName\": \"Gokeng\",\"address\":\"123 rue de paris\",\"zip\" : \"20000\", \"city\":\"Paris\",\"phone\":\"11111111\",\"email\" : \"rostow@domain.com\"}";
//	    mockMvc.perform(MockMvcRequestBuilders.post("/person")
//	      .content(person).contentType(MediaType.APPLICATION_JSON_UTF8)
//	      .andExpect(MockMvcResultMatchers.status().isOk())
//	      .andExpect(MockMvcResultMatchers.content()
//	        .contentType(textPlainUtf8));
//	}
//
//	@Test
//	public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
//	    String user = "{\"firstname\": \"\", \"email\" : \"bob@domain.com\"}";
//	    mockMvc.perform(MockMvcRequestBuilders.post("/users")
//	      .content(user)
//	      .contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isBadRequest());
////	      .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name is mandatory")))
////	      .andExpect(MockMvcResultMatchers.content()
////	        .contentType(MediaType.APPLICATION_JSON_UTF8));
//	}
//}
//
////	@Test
////	public void getPersonsTest() throws Exception {
////		mockMvc.perform(get("/persons")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is("John")));
////	}
//
////	@Test
////	public void testGetInterviewByIdFound() throws Exception {
////		when(interviewServiceImpl.getInterviewById(1)).thenReturn(mockEntity);
////		mockMvc.perform(get("/interview/1")).andExpect(status().isOk())
////				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
////				.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.type").value("hr"));
////	}
////
////	@Test
////	public void testGetInterviewByIdNotFound() throws Exception {
////		when(interviewServiceImpl.getInterviewById(1)).thenReturn(null);
////		mockMvc.perform(get("/interview/1")).andExpect(status().isNotFound());
////
////	}
////
////	@Test
////	public void testCreateOrUpdateInterview() throws Exception {
////		doReturn(mockEntity).when(interviewServiceImpl).createOrUpdateInterviewEntity(mockEntity);
////		mockMvc.perform(post("/interview").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockEntity)))
////				.andExpect(status().isCreated());
////	}
////
////	static String asJsonString(final Object obj) {
////		try {
////			return new ObjectMapper().writeValueAsString(obj);
////		} catch (Exception e) {
////			throw new RuntimeException("error when wrap json");
////		}
////	}
