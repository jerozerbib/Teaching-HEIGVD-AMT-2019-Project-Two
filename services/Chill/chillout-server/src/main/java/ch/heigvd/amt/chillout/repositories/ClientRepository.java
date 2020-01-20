package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.api.model.Client;
import ch.heigvd.amt.chillout.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String>{
    Page<Client> findAll(Pageable pageable);

}
