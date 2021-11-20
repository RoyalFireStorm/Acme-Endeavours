package acme.features.officer.duty;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface OfficerTaskRepository extends AbstractRepository {

	@Query("select t from Duty t where t.id = ?1")
	Duty findOneTaskById(int id);
	
	@Query("select t from Duty t where t.officer.userAccount.username = ?1 order by start_moment, end_moment, workload")
	List<Duty> findAllTaskByOfficerUsername(String name);
	
	@Query("select m from Officer m where m.userAccount.username = ?1")
	Officer findOfficerByUsername(String username);
	
	@Query("select t from Duty t")
	List<Duty> findAllTasks();
	
	@Query("select m from Officer m where m.id = ?1")
	Officer findOfficerById(int id);
	
}