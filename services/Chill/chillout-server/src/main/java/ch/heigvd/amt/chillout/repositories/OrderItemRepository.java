package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.entities.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
}
