package ch.heigvd.amt.usermanager.repositories;

import ch.heigvd.amt.usermanager.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long>{

}