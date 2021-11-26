package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class PanicTest extends AcmeEndeavoursTest{

	// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------

		//en este test probaremos la navegación a la vista de Panic
		//se espera que sea correcta en el caso positivo e incorrecta en el negativo
		//POSIBLE PROBLEMA: el nombre de las vistas puede cambiar
		 
		@Test
		@Order(10)
		public void positivePanic() {
			super.navigate("/master/oops", "");
			super.checkPanicExists();

		}

		@Test
		@Order(10)
		public void negativePanic() {
			super.navigate("/master/welcome", "");
			super.checkNotPanicExists();

		}
		// Ancillary methods ------------------------------------------------------
}
