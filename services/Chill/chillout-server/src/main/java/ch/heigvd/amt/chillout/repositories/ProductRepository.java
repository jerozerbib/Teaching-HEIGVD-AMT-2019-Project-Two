package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.api.model.ProductOutput;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>{
    Page<ProductOutput> findAll(Pageable pageable);

}
