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
import safety.net.alerts.controller.MedicalRecordRestService;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;

@SpringBootTest(classes = SafetyNetAlertsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MedicalRecordControllerIT {

	@Autowired
	private MedicalRecordRestService medicalRecordRestService;
	HttpHeaders headers = new HttpHeaders();

	MedicalRecord medicalRecord = new MedicalRecord();
	MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

	@Test
	public void testGetAllMedicalRecords() throws Exception {
		assertNotNull(medicalRecordRestService.getAllMedicalRecord());
	}

	@Test
	public void testGetMedicalRecord_whenMedicalRecordExists() throws Exception {
		assertEquals(medicalRecordRestService.getMedicalRecord("John", "Boyd").getBirthdate(), "03/06/1984");
	}

	@Test
	public void testGetMedicalRecord_whenMedicalRecordDoesNotExist() throws Exception {
		try {
			medicalRecordRestService.getMedicalRecord("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The medical record you want getting of the person : toto toto ,does not exists !"));
		}
	}

	@Test
	public void testGetListMedicalRecord_whenMedicalRecordExists() throws Exception {
		assertFalse(medicalRecordRestService.getListPersonByFirstNameAndLastName("Jo", "Boy").isEmpty());
	}

	@Test
	public void testGetListMedicalRecord_whenMedicalRecordDoesNotExist() throws Exception {
		try {
			medicalRecordRestService.getListPersonByFirstNameAndLastName("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The list of its medical record of toto toto, you want to get, is empty !"));
		}
	}

	@Test
	public void testAddMedicalRecord_whenMedicalRecordDoesNotExist() throws Exception {
		medicalRecord.setFirstName("Rostow");
		medicalRecord.setLastName("Gokeng");
		medicalRecord.setBirthdate("06/08/1985");
		medicalRecord.setMedications(null);
		medicalRecord.setAllergies(null);

		assertEquals(medicalRecordRestService.addMedicalRecord(medicalRecord).getBirthdate(), "06/08/1985");
	}

	@Test
	public void testAddAddMedicalRecord_whenMedicalRecordExists() throws Exception {
		try {
			testAddMedicalRecord_whenMedicalRecordDoesNotExist();
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(
					e.getMessage().contains("the medical record you want to add of Rostow Gokeng , already exists !"));
		}
	}

	@Test
	public void testUpdateMedicalRecord_whenMedicalRecordExists() throws Exception {
		medicalRecordDTO.setBirthdate("modified");
		assertEquals(medicalRecordRestService.updateMedicalRecord("Rostow", "Gokeng", medicalRecordDTO).getBirthdate(),
				"modified");
	}

	@Test
	public void testUpdateMedicalRecord_whenMedicalRecordDoesNotExists() throws Exception {
		medicalRecordDTO.setBirthdate("modified");
		try {
			medicalRecordRestService.updateMedicalRecord("toto", "toto", medicalRecordDTO);
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The medical record you want to update of toto toto does not exist !"));
		}
	}

	@Test
	public void testDeleteMedicalRecord_whenMedicalRecordExists() throws Exception {
		medicalRecordRestService.deleteMedicalRecord("Rostow", "Gokeng");
		Assertions.assertThat(medicalRecord.getBirthdate()).isNull();

	}

	@Test
	public void testDeleteMedicalRecord_whenMedicalRecordDosNotExist() throws Exception {
		try {
			medicalRecordRestService.deleteMedicalRecord("toto", "toto");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
			assertTrue(e.getMessage().contains("The medical record you want to delete of toto toto does not exist !"));
		}
	}
}
