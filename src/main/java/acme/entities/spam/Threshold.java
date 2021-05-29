package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Threshold extends DomainEntity {
	
	// Serialisation identifier -----------------------------------------------
	protected static final long	serialVersionUID	= 1L;


	// Attributes -------------------------------------------------------------
	@NotNull
	@Min(1)
	@Max(100)
	protected Double number;


	@Override
	public String toString() {
		return this.number.toString();
	}

}
	