package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

		Integer totalNumberTaskPublic;
		Integer totalNumberTaskPrivate;
		
		Integer totalNumberTaskFinished;
		Integer totalNumberTaskNoFinished;

		Double averageTaskWorkload;
		Double deviationTaskWorkload;
		Double maximumTaskWorkload;
		Double minimumTaskWorkload;
		
		Double averageTaskExtPeriod;
		Double deviationTaskExtPeriod;
		Double maximumTaskExtPeriod;
		Double minimumTaskExtPeriod;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}