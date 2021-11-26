package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorThresholdUpdateTest extends AcmeEndeavoursTest {
	
	//Test de la feature administrator/threshold/update positivo, se espera que el administrador
	//pueda updatear el valor del threshold sin problema
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/Threshold/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateThreshold(final String threshold) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam");
		super.clickOnReturnButton(AdministratorThresholdShowTest.Label);
		
		super.fillInputBoxIn("number", threshold);
		super.clickOnSubmitButton("Update Threshold");
		
		//Comprobamos que se haya updateado bien
		super.navigate("/administrator/spam/threshold/show",null);
		super.checkInputBoxHasValue("number", threshold);
		super.clickOnReturnButton("Return");
		super.signOut();

	}
	
	//Test de la feature administrator/threshold/update negativo,
	//se probaran las restricciones: 0, caracteres, numero negativo, numero entero
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/Threshold/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdateThreshold(final String threshold) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam");
		super.clickOnReturnButton(AdministratorThresholdShowTest.Label);
		
		super.fillInputBoxIn("number", threshold);
		super.clickOnSubmitButton("Update Threshold");
		
		super.checkErrorsExist();
		
		super.signOut();

	}


}
