package ch.heigvd.amt.chillout.repositories;

import ch.heigvd.amt.chillout.api.model.Order;
import ch.heigvd.amt.chillout.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    Page<Order> findAll(Pageable pageable);

}
