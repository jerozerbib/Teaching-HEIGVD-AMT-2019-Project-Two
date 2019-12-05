package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long>{

}
