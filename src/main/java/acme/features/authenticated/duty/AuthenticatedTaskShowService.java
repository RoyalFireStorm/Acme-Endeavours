package acme.features.authenticated.duty;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.duties.DutyStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Duty>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedTaskRepository repository;


		@Override
		public boolean authorise(final Request<Duty> request) {
			assert request != null;
			Duty duty;
	        int dutyId;
	        dutyId = request.getModel().getInteger("id");
	        duty = this.repository.findOneTaskById(dutyId);

	        if(duty.getStatus().equals(DutyStatus.PRIVATE) || duty.getEndMoment().after(Calendar.getInstance().getTime())) return false;
	        return true;
		}
		
		@Override
		public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "startMoment", "endMoment", "workload", 
				"status","description","link");
			model.setAttribute("confirmation", false);
			model.setAttribute("readonly", true);
		}

		@Override
		public Duty findOne(final Request<Duty> request) {
			assert request != null;

			Duty result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneTaskById(id);

			return result;
		}
}
