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

		Integer totalNumberDutyPublic;
		Integer totalNumberDutyPrivate;
		
		Integer totalNumberDutyFinished;
		Integer totalNumberDutyNoFinished;

		Double averageDutyWorkload;
		Double deviationDutyWorkload;
		Double maximumDutyWorkload;
		Double minimumDutyWorkload;
		
		Double averageDutyExtPeriod;
		Double deviationDutyExtPeriod;
		Double maximumDutyExtPeriod;
		Double minimumDutyExtPeriod;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}