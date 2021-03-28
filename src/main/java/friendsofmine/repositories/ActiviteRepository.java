package friendsofmine.repositories;

import friendsofmine.domain.Activite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends CrudRepository<Activite, Long> {
}
