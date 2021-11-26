package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerTaskDeleteTest extends AcmeEndeavoursTest {
	
	//Test del borrado de duties de un Officer (positivo). Borramos varias duties e intentamos acceder a ellas. Se espera que no se pueda ya que se han borrado con exito.
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/Duty/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDeleteTask(final int recordIndex,final int id) {
		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "List duties");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete Duty");
		super.navigate("/officer/duty/show", "id=" + id);
		super.checkErrorsExist();
		

	}

	//Test del borrado de duties de un Officer (negativo). Intentamos borrar con officer2 una duty que pertenece a officer.
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/Duty/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeDeleteTask(final int recordIndex,final int id) {
		
		super.signIn("officer", "officer");
		super.clickOnMenu("Officer", "List duties");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete Duty");
		super.signOut();
		
		super.signIn("officer2", "officer2");
		super.navigate("/officer/duty/show", "id=" + id);
		super.checkPanicExists();
 
	}
}
