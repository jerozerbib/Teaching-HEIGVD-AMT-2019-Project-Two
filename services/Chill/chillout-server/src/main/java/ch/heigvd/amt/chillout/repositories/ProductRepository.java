package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.entities.ClientEntity;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>{

}
