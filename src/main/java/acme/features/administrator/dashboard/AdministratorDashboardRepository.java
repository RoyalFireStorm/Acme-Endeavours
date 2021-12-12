package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.DutyStatus;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//Total number of public/private duties
	@Query("select count(t) from Duty t where t.status= ?1")
	Integer totalNumberDutybyStatus(DutyStatus dutyStatus);
	
	//Total number of finished/non-finished duties
	@Query("select count(t) from Duty t where t.endMoment<current_timestamp()")
	Integer totalNumberDutyFinished();
	
	@Query("select count(t) from Duty t where t.endMoment>=current_timestamp()")
	Integer totalNumberDutyNoFinished();
		
	
	//Average, deviation, minimum, and maximum duty workloads
	@Query("select avg(t.workload) from Duty t")
	Double averageDutyWorkload();
	
	@Query("select stddev(t.workload) from Duty t")
	Double deviationDutyWorkload();
	
	@Query("select max(t.workload) from Duty t")
	Double maximumDutyWorkload();
	
	@Query("select min(t.workload) from Duty t")
	Double minimumDutyWorkload();
	
	//Average, deviation, minimum, and maximum duty execution period
	@Query("select avg(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double averageDutyExtPeriod();
	
	@Query("select stddev(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double deviationDutyExtPeriod();
	
	@Query("select max(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double maximumDutyExtPeriod();
	
	@Query("select min(DATEDIFF(t.endMoment, t.startMoment)) from Duty t")
	Double minimumDutyExtPeriod();
	
}
