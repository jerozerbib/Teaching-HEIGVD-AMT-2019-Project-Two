package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.OrdersApi;
import ch.heigvd.amt.chillout.api.model.*;
import ch.heigvd.amt.chillout.entities.ClientEntity;
import ch.heigvd.amt.chillout.entities.OrderEntity;
import ch.heigvd.amt.chillout.entities.OrderItemEntity;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import ch.heigvd.amt.chillout.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class OrderApiController implements OrdersApi {

    @Autowired
    OrderRepository orderRepository;

    /**
     * Gets all the Orders in the database with the GET method request
     * @return all the clients
     */
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = new ArrayList<>();
        for (OrderEntity order : orderRepository.findAll()) {
            orders.add(toOrder(order));
        }
        return ResponseEntity.ok(orders);
    }

    /**
     * Converts a OrderEntity to an Order
     * @param entity to convert
     * @return a Order
     */
    private Order toOrder(OrderEntity entity) {
        Order order = new Order();
        order.setId(entity.getId());
        order.setClient(toClientOutput(entity.getClientEntity()));
        order.setOrderItems(toOrderItems(entity.getOrderItems()));
        return order;
    }

    /**
     * Converts a ClientEntity to a ClientOutpur
     * @param entity to convert
     * @return a ClientOutput
     */
    private ClientOutput toClientOutput(ClientEntity entity) {
        ClientOutput client = new ClientOutput();
        client.setName(entity.getName());
        client.setUsername(entity.getUsername());
        client.setId(entity.getId());
        return client;
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
            orderItem.setProduct(toProduct(entity.getProductEntity()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    /**
     * Convert a ProductEntity to a Product
     * @param entity to convert
     * @return a Product
     */
    private Product toProduct(ProductEntity entity) {
        Product product = new Product();
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setUnitPrice(entity.getUnitPrice());
        return product;
    }
}
