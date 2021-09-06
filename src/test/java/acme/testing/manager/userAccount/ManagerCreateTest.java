package acme.testing.manager.userAccount;

import org.junit.jupiter.api.Test;

import acme.testing.AcmeWorkPlansTest;

public class ManagerCreateTest extends AcmeWorkPlansTest{
	// Lifecycle management ---------------------------------------------------
	
			// Test cases -------------------------------------------------------------

			//en este test probaremos la creación de un manager
			//se espera que sea correcta la creación
			//POSIBLE PROBLEMA: introducir datos vacíos
			@Test
			public void positiveCreateManager() {
				super.navigateHome();
				
				super.signIn("administrator", "administrator");
				
				super.clickOnMenu("Account", "Become a manager");

				super.clickOnSubmitButton("Register");
				super.checkNotPanicExists();
				
				super.signOut();

				}
			
				
				

			// Ancillary methods ------------------------------------------------------

}
