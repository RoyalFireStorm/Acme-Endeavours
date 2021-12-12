package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.DutyStatus;
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
		"totalNumberDutyPublic","totalNumberDutyPrivate","totalNumberDutyFinished",
		"totalNumberDutyNoFinished","averageDutyWorkload",
		"deviationDutyWorkload","maximumDutyWorkload","minimumDutyWorkload",
		"averageDutyExtPeriod","deviationDutyExtPeriod","maximumDutyExtPeriod",
		"minimumDutyExtPeriod");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		final Integer totalNumberDutyPublic;
		final Integer totalNumberDutyPrivate;
		final Integer totalNumberDutyFinished;
		final Integer totalNumberDutyNoFinished;
		final Double averageDutyWorkload;
		final Double deviationDutyWorkload;
		final Double maximumDutyWorkload;
		final Double minimumDutyWorkload;
		final Double averageDutyExtPeriod;
		final Double deviationDutyExtPeriod;
		final Double maximumDutyExtPeriod;
		final Double minimumDutyExtPeriod;

		totalNumberDutyPublic = this.repository.totalNumberDutybyStatus(DutyStatus.PUBLIC);
		totalNumberDutyPrivate = this.repository.totalNumberDutybyStatus(DutyStatus.PRIVATE);
		
		totalNumberDutyFinished = this.repository.totalNumberDutyFinished();
		totalNumberDutyNoFinished= this.repository.totalNumberDutyNoFinished();
		
		averageDutyWorkload = this.repository.averageDutyWorkload();
		deviationDutyWorkload = this.repository.deviationDutyWorkload();
		maximumDutyWorkload = this.repository.maximumDutyWorkload();
		minimumDutyWorkload= this.repository.minimumDutyWorkload();
		
		averageDutyExtPeriod = this.repository.averageDutyExtPeriod();
		deviationDutyExtPeriod = this.repository.deviationDutyExtPeriod();
		maximumDutyExtPeriod = this.repository.maximumDutyExtPeriod();
		minimumDutyExtPeriod= this.repository.minimumDutyExtPeriod();
		
		result = new Dashboard();
		result.setTotalNumberDutyPublic(totalNumberDutyPublic);
		result.setTotalNumberDutyPrivate(totalNumberDutyPrivate);
		result.setTotalNumberDutyFinished(totalNumberDutyFinished);
		result.setTotalNumberDutyNoFinished(totalNumberDutyNoFinished);
		result.setAverageDutyWorkload(averageDutyWorkload);
		result.setDeviationDutyWorkload(deviationDutyWorkload);
		result.setMaximumDutyWorkload(maximumDutyWorkload);
		result.setMinimumDutyWorkload(minimumDutyWorkload);
		result.setAverageDutyExtPeriod(averageDutyExtPeriod);
		result.setDeviationDutyExtPeriod(deviationDutyExtPeriod);
		result.setMaximumDutyExtPeriod(maximumDutyExtPeriod);
		result.setMinimumDutyExtPeriod(minimumDutyExtPeriod);
		

		return result;
	}

}
