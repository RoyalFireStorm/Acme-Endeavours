package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AnonymousShoutListTest extends AcmeEndeavoursTest {

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------

		//en este test probaremos el listado de Shout de la feature anonymous/shout/list , no hay posibilidad de show, por lo que solo probaremos que se muestra en el orden correspondiente
		//se espera que sea correcto el orden de los Shout
		//POSIBLE PROBLEMA: con el tiempo el orden se puede ver cambiado dado que se muestran los Shout de menos de 1 mes
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/Shout/listPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveListShout(final int recordIndex, final String moment, final String author, final String text, final String info) {
			super.navigateHome();
			super.clickOnMenu("Anonymous", "List shouts");
			
			super.checkColumnHasValue(recordIndex, 0, moment);
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
			
//			super.checkInputBoxHasValue("Author", author);
//			super.checkInputBoxHasValue("Text", text);
//			super.checkInputBoxHasValue("Moment", moment);
//			super.checkInputBoxHasValue("Info", info);
			
			super.navigateHome();

		}

		// Ancillary methods ------------------------------------------------------

}
