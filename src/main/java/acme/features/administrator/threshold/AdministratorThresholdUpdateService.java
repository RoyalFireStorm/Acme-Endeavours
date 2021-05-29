package acme.features.administrator.threshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Threshold;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorThresholdRepository repository;

		// AbstractListService<Administrator, Threshold> -------------------------------------


		@Override
		public boolean authorise(final Request<Threshold> request) {
			this.validateRequest(request);
			return true;
		}

		@Override
		public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
			this.validate(request,  entity, errors);
			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
			this.validateRequest(request);
			assert entity != null;
			assert model != null;
			request.unbind(entity, model, "number");
		}

		@Override
		public Threshold findOne(final Request<Threshold> request) {
			this.validateRequest(request);
			return this.repository.findThreshold();
		}
		
		@Override
		public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
			this.validateRequest(request);
			assert entity != null;
			assert errors != null;	
		}

		@Override
		public void update(final Request<Threshold> request, final Threshold entity) {
			this.validateRequest(request);
			assert entity != null;

			this.repository.save(entity);
		}
		
		@Override
	    public void onSuccess(final Request<Threshold> request, final Response<Threshold> response) {
			this.validateRequest(request);
	        assert response != null;

	        if (request.isMethod(HttpMethod.POST)) {
	            PrincipalHelper.handleUpdate();
	        }
	    }
		private void validateRequest(final Request<Threshold> request) {
			assert request != null;
		}

}
