package acme.features.anonymous.task;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.tasks.TaskStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		assert request !=null;
		Task task;
        int taskId;
        taskId = request.getModel().getInteger("id");
        task = this.repository.findOneTaskById(taskId);

        if(task.getStatus().equals(TaskStatus.PRIVATE) || task.getEndMoment().before(Calendar.getInstance().getTime())) return false;
        return true;
	}
	
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startMoment", "endMoment", "workload", 
			"status","description","link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
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
}
