package acme.testing.authenticated.duty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeEndeavoursTest;

public class AuthenticatedDutyListAndShowTest extends AcmeEndeavoursTest {

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------

		//en este test probaremos el listado de Duty, probaremos que se muestra en el orden correspondiente
		//se espera que sea correcto el orden de las Duty
		//POSIBLE PROBLEMA: con el tiempo el contenido se puede ver cambiado dado que se muestran las Duty del populate Sample actual
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/Duty/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveAuthenticatedListAndShowDuty(final int recordIndex, final String title, final String startMoment, final String endMoment,final String description ,final String workload,final String status,final String link) {
			super.navigateHome();
			
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Authenticated", "List Duty");
			
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, startMoment);
			super.checkColumnHasValue(recordIndex, 2, endMoment);
			super.checkColumnHasValue(recordIndex, 3, workload);

			super.clickOnListingRecord(recordIndex);
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("startMoment", startMoment);
			super.checkInputBoxHasValue("endMoment", endMoment);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("workload", workload);
			super.checkInputBoxHasValue("status", status);
			super.checkInputBoxHasValue("link", link);

			super.signOut();
			
			}
		
			
			//en este test probaremos el listado de Duty, probaremos que no se muestra debido a que no estamos con la sesión iniciada
			//tambien probamos que no muestra el contenido de ningún elemento del listado
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/Duty/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
			public void negativeAuthenticatedListandShowDuty(final int recordIndex, final String title, final String startMoment, final String endMoment,final String description ,final String workload,final String status,final String link, final String id) {
				super.signIn("administrator", "administrator");
				super.signOut();
				final String path="/authenticated/duty/list";
				final String query=null;
				
				super.navigate(path, query);
				
				super.checkErrorsExist();
				super.checkPanicExists();
				
				final String query2="id="+id;
				final String path2="/authenticated/duty/list/show";

				super.navigate(path2, query2);
				
				super.checkPanicExists();
		}

		// Ancillary methods ------------------------------------------------------

}
