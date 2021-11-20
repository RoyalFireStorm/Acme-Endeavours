package acme.features.anonymous.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Duty>{

	@Autowired
	AnonymousTaskRepository repository;
	
	
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
		result = this.repository.findNotFinishedByExecutionPeriod();
		
		return result;
	}

}
