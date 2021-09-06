package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerTaskUpdateTest   extends AcmeWorkPlansTest  {

	//Test de la actualización de una task de un Manager (positivo). Introducimos valores correctos para cada campo del formulario y actualizamos la task.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update Task");
		
		super.checkSimplePath("/manager/task/list");

	}
	
	//Test de la actualización de una task de un Manager (negativo). Introducimos valores incorrectos en los campos del formulario e intentamos actualizar la task.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Task/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdate(final int recordIndex, final String title, final String startMoment, 
		final String endMoment,final String workload,final String status, final String description, final String link) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update Task");
		super.checkErrorsExist();	


	}

}
