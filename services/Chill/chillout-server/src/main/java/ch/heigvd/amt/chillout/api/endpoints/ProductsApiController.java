package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ProductsApi;
import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.InlineObject;
import ch.heigvd.amt.chillout.api.model.Order;
import ch.heigvd.amt.chillout.api.model.ProductInput;
import ch.heigvd.amt.chillout.api.model.ProductOutput;
import ch.heigvd.amt.chillout.api.service.ProductService;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import ch.heigvd.amt.chillout.repositories.ProductRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@RestController
public class ProductsApiController implements ProductsApi {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    /**
     * Create a Product
     * @param product
     * @return
     * @throws ApiException
     */
    public ResponseEntity<Object> createProduct(@ApiParam(value = "", required = true) @Valid @RequestBody ProductInput product) throws ApiException {
        ProductEntity productEntity = productService.createProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(productEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all the Products
     * @param page
     * @param pageSize
     * @return
     * @throws ApiException
     */
    public ResponseEntity<List<ProductOutput>> getProducts(@Min(1)@ApiParam(value = "Number of the page", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page, @Min(1)@ApiParam(value = "Size of the page", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws ApiException {
        List<ProductOutput> products = productService.getProducts(page,pageSize);
        return ResponseEntity.ok(products);
    }

    /**
     * Gets a a Product by his id
     * @param id
     * @return
     * @throws ApiException
     */
    public ResponseEntity<ProductOutput> getProductById(@ApiParam(value = "The email of the Product",required=true) @PathVariable("id") Long id) throws ApiException {
        ProductEntity entity = productService.getProductById(id);
        return ResponseEntity.ok(productService.toProduct(entity));
    }

    /**
     * Updates a Produt
     * @param id
     * @param fields
     * @return
     * @throws ApiException
     */
    public ResponseEntity<ProductOutput> updateProduct(@ApiParam(value = "The id of the Product",required=true) @PathVariable("id") Long id, @ApiParam(value = ""  )  @Valid @RequestBody InlineObject fields) throws ApiException{
        productService.updateProduct(id, fields);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a Product
     * @param id
     * @return
     * @throws ApiException
     */
    public ResponseEntity<Object> deleteProductById(@ApiParam(value = "The id of the Product",required=true) @PathVariable("id") Long id) throws ApiException {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
