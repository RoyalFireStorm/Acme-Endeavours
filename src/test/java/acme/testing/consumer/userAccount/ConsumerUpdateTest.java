package acme.testing.consumer.userAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeWorkPlansTest;

public class ConsumerUpdateTest extends AcmeWorkPlansTest{
		
	//Test de update consumer, se espera que un consumer pueda actualizar sus datos
		@ParameterizedTest
		@CsvFileSource(resources = "/consumer/userAccount/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveUpdateConsumer(final String Company,final String Sector) {
			super.navigateHome();
			
			super.signIn("consumer", "consumer");
			
			super.clickOnMenu("Account", "Consumer data");
			super.fillInputBoxIn("company", Company);
			super.fillInputBoxIn("sector", Sector);

			super.clickOnSubmitButton("Update");
			
			//Comprobamos que se haya updateado bien
			super.navigate("/authenticated/consumer/update",null);
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
		@CsvFileSource(resources = "/consumer/userAccount/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeUpdateConsumer(final String Company,final String Sector) {
			super.navigateHome();
			
			super.signIn("consumer", "consumer");
			
			super.clickOnMenu("Account", "Consumer data");
			super.fillInputBoxIn("company", Company);
			super.fillInputBoxIn("sector", Sector);
	
			super.clickOnSubmitButton("Update");
			
			super.checkErrorsExist();
			
			super.signOut();
	
		}

}