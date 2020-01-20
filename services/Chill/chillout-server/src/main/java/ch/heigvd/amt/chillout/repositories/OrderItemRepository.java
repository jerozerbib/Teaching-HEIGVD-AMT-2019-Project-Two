package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.api.model.OrderItem;
import ch.heigvd.amt.chillout.entities.OrderItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
    Page<OrderItem> findAll(Pageable pageable);

}
