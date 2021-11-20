package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorListSpamTest extends AcmeWorkPlansTest{

	
	//Test de la feature administrator/spam/list positivo, se espera que un administrador
	//no tenga problemas en acceder al listado de palabras spam
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/listPositive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void listAll(final int recordIndex, final String spamWords) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam");
		super.checkColumnHasValue(recordIndex, 0, spamWords);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("spamWords", spamWords);
		
		super.signOut();
	}
	@Test
	@Order(11)
	public void spamNegative() {
		
		super.signIn("officer", "officer");
		
		super.navigate("/administrator/spam", "list");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	
}
