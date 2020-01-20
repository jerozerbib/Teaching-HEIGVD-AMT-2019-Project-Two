package ch.heigvd.amt.chillout.api.service;

import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.Order;
import ch.heigvd.amt.chillout.api.model.OrderItem;
import ch.heigvd.amt.chillout.api.model.Product;
import ch.heigvd.amt.chillout.entities.ClientEntity;
import ch.heigvd.amt.chillout.entities.OrderEntity;
import ch.heigvd.amt.chillout.entities.OrderItemEntity;
import ch.heigvd.amt.chillout.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    /**
     * Creates an Order
     * @param order
     * @param email
     * @return
     */
    public OrderEntity createOrder(@Valid List<OrderItem> order, String email) {

        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        for (OrderItem o: order){
            orderItemEntityList.add(toOrderItemEntity(o));
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderItems(orderItemEntityList);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(email);

        orderEntity.setClientEntity(clientEntity);
        orderRepository.save(orderEntity);
        return orderEntity;

    }

    /**
     * Get all the Orders
     * @param numPage
     * @param pageSize
     * @return
     */
    public List<Order> getOrders(@Min(1) @Valid Integer numPage, @Min(1) @Valid Integer pageSize) {

        Pageable paging = PageRequest.of(numPage,pageSize);
        Page<Order> pagedResult = orderRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * Gets an Order by its ID
     * @param id
     * @return
     * @throws ApiException
     */
    public OrderEntity getOrderById(String id) throws ApiException {

        OrderEntity orderEntity = orderRepository.findById(Long.valueOf(id)).orElse(null);
        if (orderEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND,"Order not found");
        }
        return orderEntity;
    }

    /**
     * Deletes an Order by its ID
     * @param id
     * @throws ApiException
     */
    public void deleteOrderById(String id) throws ApiException {
        OrderEntity orderEntity = getOrderById(id);
        orderRepository.delete(orderEntity);
    }


    /**
     * Converts a OrderEntity to an Order
     * @param entity to convert
     * @return a Order
     */
    public Order toOrder(OrderEntity entity) {
        Order order = new Order();
        order.setId(entity.getId());
        order.setClient(clientService.toClient(entity.getClientEntity()));
        order.setOrderItems(toOrderItems(entity.getOrderItems()));
        return order;
    }

    /**
     * Convert a list of OrderItemEntities to a list of OrderItems
     * @param entities to convert
     * @return a list of OrderItems
     */
    private List<OrderItem> toOrderItems(List<OrderItemEntity> entities) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemEntity entity : entities) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(entity.getId());
            orderItem.setQuantity(entity.getQuantity());
            orderItem.setProduct(productService.toProduct(entity.getProductEntity()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    /**
     * Converts an OrderItem to an OrderItemEntity
     * @param order
     * @return
     */
    private OrderItemEntity toOrderItemEntity(@Valid OrderItem order) {

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setProductEntity(productService.toProductEntity(order.getProduct()));
        orderItemEntity.setQuantity(order.getQuantity());
        return orderItemEntity;

    }
}