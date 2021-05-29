package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamListService implements AbstractListService<Administrator, Spam>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSpamRepository repository;

		// AbstractListService<Administrator, Spam> interface --------------


		@Override
		public boolean authorise(final Request<Spam> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "spamWords");
		}

		@Override
		public Collection<Spam> findMany(final Request<Spam> request) {
			assert request != null;

			Collection<Spam> result;

			result = this.repository.findAllSpams();

			return result;
		}

}
