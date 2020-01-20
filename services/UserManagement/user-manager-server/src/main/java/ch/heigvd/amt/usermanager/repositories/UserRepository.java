package ch.heigvd.amt.usermanager.repositories;

import ch.heigvd.amt.usermanager.api.model.UserOutput;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, String>{
    /**
     * Add pagination
     * @param pageable
     * @return
     */
    Page<UserOutput> findAll(Pageable pageable);
}