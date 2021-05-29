package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.TaskStatus;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
		"totalNumberTaskPublic","totalNumberTaskPrivate","totalNumberTaskFinished",
		"totalNumberTaskNoFinished","averageTaskWorkload",
		"deviationTaskWorkload","maximumTaskWorkload","minimumTaskWorkload",
		"averageTaskExtPeriod","deviationTaskExtPeriod","maximumTaskExtPeriod",
		"minimumTaskExtPeriod");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		final Integer totalNumberTaskPublic;
		final Integer totalNumberTaskPrivate;
		final Integer totalNumberTaskFinished;
		final Integer totalNumberTaskNoFinished;
		final Double averageTaskWorkload;
		final Double deviationTaskWorkload;
		final Double maximumTaskWorkload;
		final Double minimumTaskWorkload;
		final Double averageTaskExtPeriod;
		final Double deviationTaskExtPeriod;
		final Double maximumTaskExtPeriod;
		final Double minimumTaskExtPeriod;

		totalNumberTaskPublic = this.repository.totalNumberTaskbyStatus(TaskStatus.PUBLIC);
		totalNumberTaskPrivate = this.repository.totalNumberTaskbyStatus(TaskStatus.PRIVATE);
		
		totalNumberTaskFinished = this.repository.totalNumberTaskFinished();
		totalNumberTaskNoFinished= this.repository.totalNumberTaskNoFinished();
		
		averageTaskWorkload = this.repository.averageTaskWorkload();
		deviationTaskWorkload = this.repository.deviationTaskWorkload();
		maximumTaskWorkload = this.repository.maximumTaskWorkload();
		minimumTaskWorkload= this.repository.minimumTaskWorkload();
		
		averageTaskExtPeriod = this.repository.averageTaskExtPeriod();
		deviationTaskExtPeriod = this.repository.deviationTaskExtPeriod();
		maximumTaskExtPeriod = this.repository.maximumTaskExtPeriod();
		minimumTaskExtPeriod= this.repository.minimumTaskExtPeriod();
		
		result = new Dashboard();
		result.setTotalNumberTaskPublic(totalNumberTaskPublic);
		result.setTotalNumberTaskPrivate(totalNumberTaskPrivate);
		result.setTotalNumberTaskFinished(totalNumberTaskFinished);
		result.setTotalNumberTaskNoFinished(totalNumberTaskNoFinished);
		result.setAverageTaskWorkload(averageTaskWorkload);
		result.setDeviationTaskWorkload(deviationTaskWorkload);
		result.setMaximumTaskWorkload(maximumTaskWorkload);
		result.setMinimumTaskWorkload(minimumTaskWorkload);
		result.setAverageTaskExtPeriod(averageTaskExtPeriod);
		result.setDeviationTaskExtPeriod(deviationTaskExtPeriod);
		result.setMaximumTaskExtPeriod(maximumTaskExtPeriod);
		result.setMinimumTaskExtPeriod(minimumTaskExtPeriod);
		

		return result;
	}

}
