package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
