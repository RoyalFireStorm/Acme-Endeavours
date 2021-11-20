package acme.testing.manager.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerTaskCreateTest  extends AcmeWorkPlansTest  {
	
	//Test de la creación de duties de un Manager (positivo). Introducimos valores correctos para cada campo del formulario y creamos la duty.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Duty/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateTask(final String title, final String startMoment, final String endMoment,final String workload,final String status, final String description,  final String link) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create duty");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmitButton("Create Duty");
		
		super.checkSimplePath("/master/welcome");
	}

	
	//Test de la creación de duties de un Manager (negativo). Introducimos valores erroneos en los campos del formulario. No nos dejará crear la duty ya que saltará el validador.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Duty/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateTask(final String title, final String startMoment, final String endMoment, final String workload, final String status, final String description,final String link) {
		
		super.signIn("manager", "manager");
		super.clickOnMenu("Manager", "Create duty");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startMoment", startMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmitButton("Create Duty");
		super.checkErrorsExist();	
	}
	
}
