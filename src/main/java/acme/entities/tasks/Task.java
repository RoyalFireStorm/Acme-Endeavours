package acme.entities.tasks;

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

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{
	
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
	protected TaskStatus status;
	
	//Relationships -----------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;

	@Override
	public String toString() {
		return "Task [title=" + this.title + ", startMoment=" + this.startMoment + ", endMoment=" + this.endMoment + ", description=" + this.description + ", workload=" + this.workload + ", link=" + this.link + ", status=" + this.status + ", manager=" + this.manager + "]";
	}

	
	
}
