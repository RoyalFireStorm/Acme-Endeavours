package acme.testing.officer.userAccount;

import org.junit.jupiter.api.Test;

import acme.testing.AcmeWorkPlansTest;

public class OfficerCreateTest extends AcmeWorkPlansTest{
	// Lifecycle management ---------------------------------------------------
	
			// Test cases -------------------------------------------------------------

			//en este test probaremos la creación de un officer
			//se espera que sea correcta la creación
			//POSIBLE PROBLEMA: introducir datos vacíos
			@Test
			public void positiveCreateOfficer() {
				super.navigateHome();
				
				super.signIn("administrator", "administrator");
				
				super.clickOnMenu("Account", "Become a officer");

				super.clickOnSubmitButton("Register");
				super.checkNotPanicExists();
				
				super.signOut();

				}
			
				
				

			// Ancillary methods ------------------------------------------------------

}
