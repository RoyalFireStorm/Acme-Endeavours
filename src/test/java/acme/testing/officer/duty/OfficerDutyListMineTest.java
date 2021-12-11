package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyListMineTest  extends AcmeEndeavoursTest {
	
	//Test de la lista de duties de un Officer. Se espera que cada columna tenga el valor asignado en /listTask/positive.csv
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/Duty/listPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveListShowTask(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "List duties");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);

	}
	
	//En este test probaremos el listado y vista de Duties, probaremos que no se muestra debido a que no no hemos iniciado sesión como officer
	@Test
	@Order(10)
	public void negativeListShowTask() {
		
		super.signIn("officer", "officer");
		super.signOut();
		
		super.navigate("/officer/duty/list", null);
		super.checkPanicExists();
	}

}
