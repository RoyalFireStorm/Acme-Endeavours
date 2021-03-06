package acme.features.officer.duty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
@Service
public class OfficerDutyUpdateService implements AbstractUpdateService<Officer, Duty>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected OfficerDutyRepository repository;
	
	@Autowired
	protected SpamService spamService;
	@Autowired
	protected AdministratorThresholdRepository thresholdRepository;

	// AbstractUpdateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		
		Boolean result;
		int dutyId;
		
		dutyId = request.getModel().getInteger("id");
		final Duty duty = this.repository.findOneDutyById(dutyId);
		result = duty.getOfficer().getUserAccount().getId()==request.getPrincipal().getAccountId();
		
		return result;
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
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Double threshold = this.thresholdRepository.findThreshold().getNumber();;
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
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Duty> request, final Response<Duty> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
