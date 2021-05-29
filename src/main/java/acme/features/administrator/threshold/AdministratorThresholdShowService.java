package acme.features.administrator.threshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Threshold;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorThresholdShowService implements AbstractShowService<Administrator, Threshold>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorThresholdRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Threshold> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "number");
		
	}

	@Override
	public Threshold findOne(final Request<Threshold> request) {
		assert request != null;

		Threshold result;
		result = this.repository.findThreshold();

		return result;
	}



}
