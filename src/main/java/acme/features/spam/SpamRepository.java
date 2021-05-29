package acme.features.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

public interface SpamRepository extends AbstractRepository{

	
	@Query("select s from Spam s")
	List<Spam> findAllSpam();
}
