package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ProductsApi;
import ch.heigvd.amt.chillout.api.model.Product;
import ch.heigvd.amt.chillout.api.model.ProductInput;
import ch.heigvd.amt.chillout.api.model.ProductOutput;
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

    /**
     * Creates a new Product from the ProductInput of the Swagger file with a POST method request
     * @param product ProductInput that gives all the needed information necessary to create a Product
     * @return a new Object
     */
    public ResponseEntity<Object> createProduct(@ApiParam(value = "", required = true) @Valid @RequestBody ProductInput product) {
        ProductEntity newProductEntity = toProductEntity(product);
        productRepository.save(newProductEntity);
        Long id = newProductEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProductEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all the Products with a GET method request
     * @return all the products
     */
    public ResponseEntity<List<ProductOutput>> getProducts() {
        List<ProductOutput> products = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            products.add(toProduct(productEntity));
        }
        return ResponseEntity.ok(products);
    }

    /**
     * Converts a ProductInput into a ProductEntity
     * @param product to convert
     * @return a ProductEntity
     */
    private ProductEntity toProductEntity(ProductInput product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setUnitPrice(product.getUnitPrice());
        entity.setDescription(product.getDescription());
        return entity;
    }

    /**
     * Converts a ProductEntitty
     * @param entity to convert
     * @return a ProductOutput
     */
    private ProductOutput toProduct(ProductEntity entity) {
        ProductOutput product = new ProductOutput();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setUnitPrice(entity.getUnitPrice());
        product.setDescription(entity.getDescription());
        return product;
    }

}
