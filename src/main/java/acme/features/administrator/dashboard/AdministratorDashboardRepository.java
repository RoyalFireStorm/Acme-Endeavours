package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.TaskStatus;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//Total number of public/private tasks
	@Query("select count(t) from Task t where t.status= ?1")
	Integer totalNumberTaskbyStatus(TaskStatus taskStatus);
	
	//Total number of finished/non-finished tasks
	@Query("select count(t) from Task t where t.endMoment<current_timestamp()")
	Integer totalNumberTaskFinished();
	
	@Query("select count(t) from Task t where t.endMoment>=current_timestamp()")
	Integer totalNumberTaskNoFinished();
		
	
	//Average, deviation, minimum, and maximum task workloads
	@Query("select avg(t.workload) from Task t")
	Double averageTaskWorkload();
	
	@Query("select stddev(t.workload) from Task t")
	Double deviationTaskWorkload();
	
	@Query("select max(t.workload) from Task t")
	Double maximumTaskWorkload();
	
	@Query("select min(t.workload) from Task t")
	Double minimumTaskWorkload();
	
	//Average, deviation, minimum, and maximum task execution period
	@Query("select avg(DATEDIFF(t.endMoment, t.startMoment)) from Task t")
	Double averageTaskExtPeriod();
	
	@Query("select stddev(DATEDIFF(t.endMoment, t.startMoment)) from Task t")
	Double deviationTaskExtPeriod();
	
	@Query("select max(DATEDIFF(t.endMoment, t.startMoment)) from Task t")
	Double maximumTaskExtPeriod();
	
	@Query("select min(DATEDIFF(t.endMoment, t.startMoment)) from Task t")
	Double minimumTaskExtPeriod();
	
}
