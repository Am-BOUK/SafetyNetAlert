package safety.net.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgeCalculateurTest {

	private DateTimeFormatter formatDate;
	private LocalDate currentDate;
	private String birthdate;

	@BeforeEach
	private void setUpPerTest() {
		formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	}

	@Test
	public void agePersonTest_whenPersonHasThirtyFour() throws Exception {
		int age = 34;

		currentDate = LocalDate.now().minusYears(age);
		birthdate = formatDate.format(currentDate);

		int result = AgeCalculator.agePerson(birthdate);

		assertThat(age).isEqualTo(result);
	}

	@Test
	public void agePersonTest_whenPersonHasEleven() throws Exception {
		int age = 11;

		currentDate = LocalDate.now().minusYears(age);
		birthdate = formatDate.format(currentDate);

		int result = AgeCalculator.agePerson(birthdate);

		assertThat(age).isEqualTo(result);
	}

	@Test
	public void agePersonTest_whenBabyBeforeOneYear_shouldReturnOne() throws Exception {
		currentDate = LocalDate.now().minusMonths(2);
		birthdate = formatDate.format(currentDate);

		int age = AgeCalculator.agePerson(birthdate);

		assertThat(age).isEqualTo(1);
	}

	@Test
	public void agePersonTest_whenInvalidFormat() {
		String birthdate = "22/02/1999";
		try {
			AgeCalculator.agePerson(birthdate);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
		}
	}

	@Test
	public void agePersonTest_whenBirthdateAfterCurrentDate() {
		String birthdate = "02/22/2999";
		try {
			AgeCalculator.agePerson(birthdate);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertThat(e.getMessage()).contains("Error, birthdate after current date !");
		}
	}

	@Test
	void isChildTest_whenChild_shouldReturnTrue() throws Exception {
		boolean isChild;
		isChild = AgeCalculator.isChild("02/22/2019");
		assertThat(isChild).isTrue();
	}
	@Test
	void isChildTest_whenAdult_shouldReturnFalse() throws Exception {
		boolean isChild;
		isChild = AgeCalculator.isChild("02/22/1999");
		assertThat(isChild).isFalse();
	}
}
