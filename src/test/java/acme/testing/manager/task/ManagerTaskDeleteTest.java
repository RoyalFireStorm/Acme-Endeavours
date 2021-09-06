package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerTaskDeleteTest extends AcmeWorkPlansTest {
	
	//Test del borrado de tasks de un Manager (positivo). Borramos varias tasks e intentamos acceder a ellas. Se espera que no se pueda ya que se han borrado con exito.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Task/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDeleteTask(final int recordIndex,final int id) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete Task");
		super.navigate("/manager/task/show", "id=" + id);
		super.checkErrorsExist();
		

	}

	//Test del borrado de tasks de un Manager (negativo). Intentamos borrar con manager2 una task que pertenece a manager.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/Task/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeDeleteTask(final int recordIndex,final int id) {
		
		super.signIn("manager", "manager");
		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete Task");
		super.signOut();
		
		super.signIn("manager2", "manager2");
		super.navigate("/manager/task/show", "id=" + id);
		super.checkPanicExists();
 
	}
}
