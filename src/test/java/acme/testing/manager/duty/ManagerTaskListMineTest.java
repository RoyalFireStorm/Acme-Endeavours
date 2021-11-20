package acme.testing.manager.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerTaskListMineTest  extends AcmeWorkPlansTest {
	
	//Test de la lista de duties de un Manager. Se espera que cada columna tenga el valor asignado en /listTask/positive.csv
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Duty/listPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveListShowTask(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List duties");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);

	}
	
	//En este test probaremos el listado y vista de Duties, probaremos que no se muestra debido a que no no hemos iniciado sesión como manager
	@Test
	@Order(10)
	public void negativeListShowTask() {
		
		super.signIn("manager", "manager");
		super.signOut();
		
		super.navigate("/manager/duty/list", null);
		super.checkPanicExists();
	}

}
