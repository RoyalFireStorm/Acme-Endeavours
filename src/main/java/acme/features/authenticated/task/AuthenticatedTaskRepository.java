package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.id=?1")
	Task findOneTaskById(int id);
	
	@Query("select t from Task t where current_timestamp > t.endMoment and t.status=0 order by t.startMoment, t.endMoment, t.workload")
	Collection<Task> findNotFinishedByExecutionPeriod();
	

	
}
