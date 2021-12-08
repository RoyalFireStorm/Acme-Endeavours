package acme.features.officer.duty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.duties.DutyStatus;
import acme.entities.roles.Officer;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class OfficerDutyCreateService implements AbstractCreateService<Officer, Duty> {

	@Autowired
	protected OfficerDutyRepository repository;
	 
	@Autowired
	protected SpamService spamService;
	@Autowired
	protected AdministratorThresholdRepository thresholdRepository;
	
	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startMoment", "endMoment", "description", "workload", "link", "status", "officer");
	
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result;
		Officer officer;
		Date startMoment;
		Date endMoment;
		
		officer = this.repository.findOfficerByUsername(request.getPrincipal().getUsername());
		
		startMoment = new Date(System.currentTimeMillis() - 2);
		endMoment = new Date(System.currentTimeMillis() - 1);

		result = new Duty();
		result.setStartMoment(startMoment);
		result.setEndMoment(endMoment);
		result.setDescription("description");
		result.setLink("http://example.org");
		result.setStatus(DutyStatus.PUBLIC);
		result.setTitle("title");
		result.setWorkload(2.50);
		result.setOfficer(officer);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Double threshold = this.thresholdRepository.findThreshold().getNumber();
		//Validacion de SPAM
		final boolean spam1= !this.spamService.filtroSpam(entity.getTitle(),threshold);
		errors.state(request, spam1, "title","officer.duty.error.spam");
		final boolean spam2= !this.spamService.filtroSpam(entity.getDescription(),threshold);
		errors.state(request, spam2, "description","officer.duty.error.spam");
		
		//Validacion fechas
		final Date startMoment = entity.getStartMoment();
		final Date endMoment = entity.getEndMoment();	
		final Calendar c1 = Calendar.getInstance();
		final Instant hoy = c1.toInstant();
		
		if(startMoment == null) {
			
		}else {
			final Instant start = startMoment.toInstant();
			final Boolean fechaInicioBien = hoy.isBefore(start);
			errors.state(request, fechaInicioBien, "startMoment","officer.duty.error.startMoment");
		}

		if(endMoment == null || startMoment == null) {
			
		}else {
			final Boolean fechaFinBien = startMoment.before(endMoment);
		errors.state(request, fechaFinBien, "endMoment","officer.duty.error.endMoment");
		}
		
		if(!errors.hasErrors("workload")) {
		//Validacion workload
		final Double workload = entity.getWorkload();
		final BigDecimal bd = new BigDecimal(String.valueOf(workload));
		final BigDecimal fPart = bd.remainder(BigDecimal.ONE);
		final BigDecimal fPartToInt = bd.subtract(bd.setScale(0, RoundingMode.FLOOR)).movePointRight(bd.scale());
		final BigDecimal minutosMax = new BigDecimal(String.valueOf(59));
		final Integer workloadInt = workload.intValue();
		//final Integer workloadDouble = (int) (workload%1.*100);
		final Boolean workloadCorrecto;
		if(workload == null || endMoment == null || startMoment == null) {
			
		}else if(workload <= 0.0){
			errors.state(request, false, "workload","officer.duty.error.workloadNegative");
	} 
			else if(workload > 99.59) {
			errors.state(request, false, "workload","officer.duty.error.workloadMax");
	} else if(fPartToInt.compareTo(minutosMax) == 1) {
		errors.state(request, false, "workload","officer.duty.error.workloadMaxMinutes");
	} else if(workloadInt > 99) {
		errors.state(request, false, "workload","officer.duty.error.workloadMaxHours");
	}
		else{ 
			final Double workloadMaxInDays = (double)(endMoment.getTime()-startMoment.getTime())/86400000;
			final Double workloadMaxInHours = workloadMaxInDays*24;
		workloadCorrecto = workload <= workloadMaxInHours && workload > 0.;
		errors.state(request, workloadCorrecto, "workload","officer.duty.error.workload");
		}
		
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		Officer officer;
		
		officer = this.repository.findOfficerByUsername(request.getPrincipal().getUsername());
		
		entity.setOfficer(officer);
		
		this.repository.save(entity);
		
	}

}
