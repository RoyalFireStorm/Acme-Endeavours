package acme.testing.anonymous.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListShowTest extends AcmePlannerTest{
	
	// Internal state ---------------------------------------------------------

		// Lifecycle management ---------------------------------------------------
 
		// Test cases -------------------------------------------------------------

		//Caso positivo: Un usuario anónimo lista las tasks y accede a una de ellas sin ningún problema. 
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/Task/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		public void positiveListAndShowTask(final int recordIndex, final String title, final String startMoment, 
			final String endMoment, final String workload, final String status, final String description,
			final String link) {
			super.navigateHome();
			super.clickOnMenu("Anonymous", "List tasks");
		
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, startMoment);
			super.checkColumnHasValue(recordIndex, 2, endMoment);
			super.checkColumnHasValue(recordIndex, 3, workload);
			
			super.clickOnListingRecord(recordIndex);
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("startMoment", startMoment);
			super.checkInputBoxHasValue("endMoment", endMoment);
			super.checkInputBoxHasValue("workload", workload);
			super.checkInputBoxHasValue("status", status);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			
		}
		
		//Caso negativo: Un usuario administrador intenta acceder a las tasks de un usuario anónimo.  
				@Test
				public void negativeShowTask() {
					super.signIn("administrator", "administrator");
					super.navigate("/anonymous/task/show", "id=263");
		            
		            super.checkErrorsExist();
		            super.signOut();
					
				}
}
