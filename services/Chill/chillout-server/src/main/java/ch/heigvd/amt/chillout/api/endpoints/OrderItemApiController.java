package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.OrderItemApi;
import ch.heigvd.amt.chillout.api.model.OrderItem;
import ch.heigvd.amt.chillout.api.model.Product;
import ch.heigvd.amt.chillout.entities.OrderItemEntity;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import ch.heigvd.amt.chillout.repositories.OrderItemRepository;
import ch.heigvd.amt.chillout.repositories.ProductRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class OrderItemApiController implements OrderItemApi {

    @Autowired
    OrderItemRepository orderItemRepository;
    ProductRepository productRepository;

    public ResponseEntity<Object> createOrderItem(@ApiParam(value = "", required = true) @Valid @RequestBody OrderItem orderItem) {
        OrderItemEntity newProductEntity = toOrderItemEntity(orderItem);
        orderItemRepository.save(newProductEntity);
        Long id = newProductEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProductEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<OrderItem>> getOrderItems() {
        List<OrderItem> products = new ArrayList<>();
        for (OrderItemEntity orderItemEntity : orderItemRepository.findAll()) {
            products.add(toOrderItem(orderItemEntity));
        }
        return ResponseEntity.ok(products);
    }


    private OrderItemEntity toOrderItemEntity(OrderItem orderItem) {
        OrderItemEntity entity = new OrderItemEntity();

        entity.setId(orderItem.getId());
        entity.setProductEntity(productToProductEntity(orderItem.getProduct()));
        entity.setQuantity(orderItem.getQuantity());
        return entity;
    }

    private OrderItem toOrderItem(OrderItemEntity entity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(entity.getId());
        orderItem.setQuantity(entity.getQuantity());
        orderItem.setProduct(productEntityToProduct(entity));
        return orderItem;
    }

    private Product productEntityToProduct(OrderItemEntity entity) {
        ProductEntity productEntity = entity.getProductEntity();
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setUnitPrice(productEntity.getUnitPrice());
        return product;
    }

    private ProductEntity productToProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setUnitPrice(product.getUnitPrice());
        return productEntity;
    }

}
