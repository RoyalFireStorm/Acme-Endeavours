package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AnonymousShoutCreateTest extends AcmeEndeavoursTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	//Este test es de casos positivos de la feature anonymous/shout/create, todos los Shouts cumplen los requisitos especificados,
	//se espera que todos los Shouts se creen sin problema
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/Shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		super.checkSimplePath("/master/welcome");

	}
	
	//En este test de la feature anonymous/shout/create, probaremos las restricciones de los Shout,
	//se espera que salten los errores correspondientes a las restricciones: 
	//momento nulo (el pasado siempre se cumplira)
	//autor en blanco, longitud entre 6 y 25
	//texto no vacio, longitud maxima de 100 y con spam
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/Shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShout(final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		super.checkErrorsExist();
		
	}

	// Ancillary methods ------------------------------------------------------

}
