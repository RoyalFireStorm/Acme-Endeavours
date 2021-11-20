package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.DutyStatus;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//Total number of public/private duties
	@Query("select count(t) from Duty t where t.status= ?1")
	Integer totalNumberTaskbyStatus(DutyStatus dutyStatus);
	
	//Total number of finished/non-finished duties
	@Query("select count(t) from Duty t where t.endMoment<current_timestamp()")
	Integer totalNumberTaskFinished();
	
	@Query("select count(t) from Duty t where t.endMoment>=current_timestamp()")
	Integer totalNumberTaskNoFinished();
		
	
	//Average, deviation, minimum, and maximum duty workloads
	@Query("select avg(t.workload) from Duty t")
	Double averageTaskWorkload();
	
	@Query("select stddev(t.workload) from Duty t")
	Double deviationTaskWorkload();
	
	@Query("select max(t.workload) from Duty t")
	Double maximumTaskWorkload();
	
	@Query("select min(t.workload) from Duty t")
	Double minimumTaskWorkload();
	
	//Average, deviation, minimum, and maximum duty execution period
	@Query("select avg(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double averageTaskExtPeriod();
	
	@Query("select stddev(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double deviationTaskExtPeriod();
	
	@Query("select max(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double maximumTaskExtPeriod();
	
	@Query("select min(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double minimumTaskExtPeriod();
	
}
