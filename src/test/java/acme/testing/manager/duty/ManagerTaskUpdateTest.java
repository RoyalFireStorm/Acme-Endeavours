package acme.testing.manager.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerTaskUpdateTest   extends AcmeWorkPlansTest  {

	//Test de la actualización de una duty de un Manager (positivo). Introducimos valores correctos para cada campo del formulario y actualizamos la duty.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Duty/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List duties");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update Duty");
		
		super.checkSimplePath("/manager/duty/list");

	}
	
	//Test de la actualización de una duty de un Manager (negativo). Introducimos valores incorrectos en los campos del formulario e intentamos actualizar la duty.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Duty/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdate(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List duties");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update Duty");
		super.checkErrorsExist();	


	}

}
