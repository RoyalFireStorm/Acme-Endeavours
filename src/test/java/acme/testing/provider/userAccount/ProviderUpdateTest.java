package acme.testing.provider.userAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeWorkPlansTest;

public class ProviderUpdateTest extends AcmeWorkPlansTest{
	
	//Test de update consumer, se espera que un consumer pueda actualizar sus datos
	@ParameterizedTest
	@CsvFileSource(resources = "/provider/userAccount/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateProvider(final String Company,final String Sector) {
		super.navigateHome();
		
		super.signIn("provider", "provider");
		
		super.clickOnMenu("Account", "Provider data");
		super.fillInputBoxIn("company", Company);
		super.fillInputBoxIn("sector", Sector);

		super.clickOnSubmitButton("Update");
		
		//Comprobamos que se haya updateado bien
		super.navigate("/authenticated/provider/update",null);
		super.checkInputBoxHasValue("company", Company);
		super.checkInputBoxHasValue("sector", Sector);
		super.clickOnReturnButton("Return");
		super.signOut();

	}
	
	//Test de update consumer, se espera que un consumer no pueda actualizar sus datos
	//se prueba que las restricciones funcionen:
	//company no blanco
	//sector no blanco
	@ParameterizedTest
	@CsvFileSource(resources = "/provider/userAccount/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdateProvider(final String Company,final String Sector) {
		super.navigateHome();
		
		super.signIn("provider", "provider");
		
		super.clickOnMenu("Account", "Provider data");
		super.fillInputBoxIn("company", Company);
		super.fillInputBoxIn("sector", Sector);

		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();

	}

}