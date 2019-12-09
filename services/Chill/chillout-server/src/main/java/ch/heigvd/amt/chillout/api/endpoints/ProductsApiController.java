package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ProductsApi;
import ch.heigvd.amt.chillout.api.model.Product;
import ch.heigvd.amt.chillout.entities.ProductEntity;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ProductsApiController implements ProductsApi {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<Object> createProduct(@ApiParam(value = "", required = true) @Valid @RequestBody Product product) {
        ProductEntity newProductEntity = toProductEntity(product);
        productRepository.save(newProductEntity);
        Long id = newProductEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProductEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            products.add(toProduct(productEntity));
        }
        return ResponseEntity.ok(products);
    }


    private ProductEntity toProductEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setUnitPrice(product.getUnitPrice());
        entity.setDescription(product.getDescription());
        return entity;
    }

    private Product toProduct(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setUnitPrice(entity.getUnitPrice());
        product.setDescription(entity.getDescription());
        return product;
    }

}
