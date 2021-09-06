package acme.testing.consumer.userAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeWorkPlansTest;

public class ConsumerCreateTest extends AcmeWorkPlansTest{
	// Lifecycle management ---------------------------------------------------
	
			// Test cases -------------------------------------------------------------
				//en este test probaremos la creación de un consumer
				//se espera que sea correcta la creación
				//POSIBLE PROBLEMA: introducir datos vacíos
				
				@ParameterizedTest
				@CsvFileSource(resources = "/consumer/userAccount/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
				@Order(10)
				public void positiveCreateConsumer(final String Company,final String Sector) {
					super.navigateHome();
					
					super.signIn("administrator", "administrator");
					
					super.clickOnMenu("Account", "Become a consumer");
					
					super.fillInputBoxIn("company", Company);
					super.fillInputBoxIn("sector", Sector);
					
					super.clickOnSubmitButton("Register");
					super.checkNotPanicExists();
					
					super.signOut();
			
				}
			//en este test probaremos la no creación de un consumer
			//se espera que sea incorrecta la creación
			//POSIBLE PROBLEMA: introducir datos vacíos
			@ParameterizedTest
			@CsvFileSource(resources = "/consumer/userAccount/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void negativeCreateConsumer(final String Company,final String Sector) {
				super.navigateHome();
				
				super.signIn("administrator", "administrator");
				super.clickOnMenu("Administrator", "Populate DB (samples)");

				super.clickOnMenu("Account", "Become a consumer");
				
				super.fillInputBoxIn("company", Company);
				super.fillInputBoxIn("sector", Sector);
				
				super.clickOnSubmitButton("Register");
				super.checkErrorsExist();
				
				super.signOut();

				}
			
			// Ancillary methods ------------------------------------------------------

}
