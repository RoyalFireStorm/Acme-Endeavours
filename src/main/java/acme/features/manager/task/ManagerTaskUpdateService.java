package acme.features.manager.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
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
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected SpamService spamService;
	@Autowired
	protected AdministratorThresholdRepository thresholdRepository;

	// AbstractUpdateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		Boolean result;
		int taskId;
		
		taskId = request.getModel().getInteger("id");
		final Task task = this.repository.findOneTaskById(taskId);
		result = task.getManager().getUserAccount().getId()==request.getPrincipal().getAccountId();
		
		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startMoment", "endMoment", "description", "workload", "link", "status", "manager");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Double threshold = this.thresholdRepository.findThreshold().getNumber();;
		//Validacion de SPAM
		final boolean spam1= !this.spamService.filtroSpam(entity.getTitle(),threshold);
		errors.state(request, spam1, "title","manager.task.error.spam");
		final boolean spam2= !this.spamService.filtroSpam(entity.getDescription(),threshold);
		errors.state(request, spam2, "description","manager.task.error.spam");
				
		//Validacion fechas
		final Date startMoment = entity.getStartMoment();
		final Date endMoment = entity.getEndMoment();	
		final Calendar c1 = Calendar.getInstance();
		final Instant hoy = c1.toInstant();
				
		if(startMoment == null) {
				
		}else {
			final Instant start = startMoment.toInstant();
			final Boolean fechaInicioBien = hoy.isBefore(start);
			errors.state(request, fechaInicioBien, "startMoment","manager.task.error.startMoment");
		}

		if(endMoment == null || startMoment == null) {
					
		}else {
			final Boolean fechaFinBien = startMoment.before(endMoment);
			errors.state(request, fechaFinBien, "endMoment","manager.task.error.endMoment");
		}
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
			errors.state(request, false, "workload","manager.task.error.workloadNegative");
	} 
			else if(workload > 99.59) {
			errors.state(request, false, "workload","manager.task.error.workloadMax");
	} else if(fPartToInt.compareTo(minutosMax) == 1) {
		errors.state(request, false, "workload","manager.task.error.workloadMaxMinutes");
	} else if(workloadInt > 99) {
		errors.state(request, false, "workload","manager.task.error.workloadMaxHours");
	}
		else{ 
			final Double workloadMaxInDays = (double)(endMoment.getTime()-startMoment.getTime())/86400000;
			final Double workloadMaxInHours = workloadMaxInDays*24;
		workloadCorrecto = workload <= workloadMaxInHours && workload > 0.;
		errors.state(request, workloadCorrecto, "workload","manager.task.error.workload");
		}
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Task> request, final Response<Task> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
