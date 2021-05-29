package acme.features.manager.task;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select t from Task t where t.manager.userAccount.username = ?1 order by start_moment, end_moment, workload")
	List<Task> findAllTaskByManagerUsername(String name);
	
	@Query("select m from Manager m where m.userAccount.username = ?1")
	Manager findManagerByUsername(String username);
	
	@Query("select t from Task t")
	List<Task> findAllTasks();
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findManagerById(int id);
	
}