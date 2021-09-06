package acme.testing.authenticated.userAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeWorkPlansTest;

public class AuthenticatedUserAccountUpdateTest extends AcmeWorkPlansTest{
	// Lifecycle management ---------------------------------------------------
	
				// Test cases -------------------------------------------------------------
					//en este test probaremos la actualización de un userAccount en nuestra página web
					//se espera que sea correcta la creación
					//POSIBLE PROBLEMA: introducir datos vacíos
					
					@ParameterizedTest
					@CsvFileSource(resources = "/authenticated/userAccount/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
					@Order(20)
					public void positiveUpdateUserAccount(final String Password,final String Name,final String Surname,final String Email) {
						super.navigateHome();
						
						super.signIn("authenticated", "authenticated");
						
						super.clickOnMenu("Account", "General data");
						
						super.fillInputBoxIn("password", Password);
						super.fillInputBoxIn("confirmation", Password);
						super.fillInputBoxIn("identity.name", Name);
						super.fillInputBoxIn("identity.surname", Surname);
						super.fillInputBoxIn("identity.email", Email);

						super.clickOnSubmitButton("Update");
						super.checkNotPanicExists();

						super.clickOnMenu("Account", "General data");
						super.checkInputBoxHasValue("identity.name", Name);
						super.checkInputBoxHasValue("identity.surname", Surname);
						super.checkInputBoxHasValue("identity.email", Email);
						
						super.signOut();
				
					}
					
					//en este test probaremos la NO actualización de un userAccount en nuestra página web
					//se espera que sea incorrecta la creación
					//POSIBLE PROBLEMA: introducir datos vacíos
					@ParameterizedTest
					@CsvFileSource(resources = "/authenticated/userAccount/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
					@Order(10)
					public void negativeUpdateUserAccount(final String Password,final String Name,final String Surname,final String Email) {
						super.navigateHome();
						
						super.signIn("authenticated2", "authenticated2");
						
						super.clickOnMenu("Account", "General data");
						
						super.fillInputBoxIn("password", Password);
						super.fillInputBoxIn("confirmation", Password);
						super.fillInputBoxIn("identity.name", Name);
						super.fillInputBoxIn("identity.surname", Surname);
						super.fillInputBoxIn("identity.email", Email);

						super.clickOnSubmitButton("Update");
						super.checkErrorsExist();
						
						super.signOut();
				
					}
					
				// Ancillary methods ------------------------------------------------------

}