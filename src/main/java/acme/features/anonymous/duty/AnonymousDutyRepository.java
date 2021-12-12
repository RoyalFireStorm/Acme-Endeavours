package acme.features.anonymous.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousDutyRepository extends AbstractRepository{
	
	@Query("select t from Duty t where t.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select t from Duty t where current_timestamp() < t.endMoment and t.status=0 order by t.startMoment, t.endMoment, t.workload")
	Collection<Duty> findNotFinishedByExecutionPeriod();
	

	
}
