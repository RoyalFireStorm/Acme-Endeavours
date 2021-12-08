package acme.features.officer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class OfficerDutyListService implements AbstractListService<Officer, Duty> {

	@Autowired
	OfficerDutyRepository repository;

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startMoment", "endMoment", "description", "workload", "link", "status");
		
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		
		Collection<Duty> result;
		//final Officer m = this.repository.findOfficerByUsername(request.getPrincipal().getUsername());
		result = this.repository.findAllTaskByOfficerUsername(request.getPrincipal().getUsername());
		//result = this.repository.findAllTasks();
		
		return result;
	}

	
}
