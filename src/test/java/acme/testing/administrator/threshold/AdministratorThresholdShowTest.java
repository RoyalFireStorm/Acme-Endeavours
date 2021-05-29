package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdShowTest  extends AcmePlannerTest {
	
	static final String Label= "Update the Spam Threshold";
	
	//Test de la feature administrator/threshold/show positivo, se espera
	//que un administrador pueda acceder al threshold sin problemas
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/Threshold/showPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShowThreshold(final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam");
		
		super.clickOnReturnButton(AdministratorThresholdShowTest.Label);
		
		super.checkInputBoxHasValue("number", threshold);
	
		super.clickOnReturnButton("Return");
		
		super.signOut();

	}
	
	//Test de la feature administrator/threshold/show negativo, se espera
	//que un rol distinto a administrador no pueda acceder al threshold
	@Test
	public void negativeShowThreshold() {
		super.navigateHome();
		
		super.navigate("/administrator/spam/threshold/show",null);
		
		super.checkPanicExists();

	}

}
