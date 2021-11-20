package acme.entities.duties;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Officer;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotEmpty
	@Length(max = 80)
	protected String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startMoment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endMoment;
	
	@NotEmpty
	@Length(max=500)
	protected String description;
	
	@NotNull
	protected Double workload;
	
	
	@URL
	protected String link;
	
	@NotNull
	protected DutyStatus status;
	
	//Relationships -----------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Officer officer;

	@Override
	public String toString() {
		return "Duty [title=" + this.title + ", startMoment=" + this.startMoment + ", endMoment=" + this.endMoment + ", description=" + this.description + ", workload=" + this.workload + ", link=" + this.link + ", status=" + this.status + ", officer=" + this.officer + "]";
	}

	
	
}
