package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorUserAccountUpdateTest extends AcmeEndeavoursTest{
	
	/*Caso positivo: Un usuario administrador actualiza los datos de las cuentas de usuario (update)
	 *  Resultado esperado: Éxito a la hora de realizar la actualización.*/ 
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final int recordIndex, final String username, final String name, 
		final String surname, final String email, final String roles, final String status, final String newStatus) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(recordIndex);
		
		if(newStatus!=null) {
		super.fillInputBoxIn("newStatus", newStatus);
		super.clickOnSubmitButton("Update");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", newStatus);
		}
		
		super.signOut();

	}
	
	/*Caso negativo: Un usuario administrador actualiza los datos de las cuentas de usuario (update)
	 *  Resultado esperado:  Debe producirse un error debido a que 
	 *  se ha introducido mal el campo "new status". Concretamente, se ha dejado vacío.*/ 
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdate(final int recordIndex, final String username, final String name, 
		final String surname, final String email, final String roles, final String status, final String newStatus) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(recordIndex);
		
		if(newStatus!=null) {
		super.fillInputBoxIn("newStatus", newStatus);
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		}
		
		super.signOut();

	}

}
