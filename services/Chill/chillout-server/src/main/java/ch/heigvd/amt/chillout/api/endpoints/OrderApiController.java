package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.OrdersApi;
import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.InlineObject1;
import ch.heigvd.amt.chillout.api.model.Order;
import ch.heigvd.amt.chillout.api.model.OrderItem;
import ch.heigvd.amt.chillout.api.service.OrdersService;
import ch.heigvd.amt.chillout.entities.OrderEntity;
import ch.heigvd.amt.chillout.repositories.OrderRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@RestController
public class OrderApiController implements OrdersApi {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<Object> createOrder(@ApiParam(value = ""  )  @Valid @RequestBody InlineObject1 fields) throws ApiException {

        List<OrderItem> order = fields.getOrder();
        String email = fields.getEmail();
        OrderEntity orderItemEntity = ordersService.createOrder(order,email);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderItemEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Order> getOrderById(@ApiParam(value = "The id of the order",required=true) @PathVariable("id") String id) throws ApiException {
        OrderEntity orderEntity = ordersService.getOrderById(id);
        return ResponseEntity.ok(ordersService.toOrder(orderEntity));
    }

    public ResponseEntity<List<Order>> getOrders(@Min(1)@ApiParam(value = "Number of the page", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page, @Min(1)@ApiParam(value = "Size of the page", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws ApiException {

        List<Order> orders = ordersService.getOrders(page,pageSize);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<Object> deleteOrder(@ApiParam(value = "The id of the order",required=true) @PathVariable("id") String id) throws ApiException{

       ordersService.deleteUserById(id);
       return ResponseEntity.ok().build();
    }






}
