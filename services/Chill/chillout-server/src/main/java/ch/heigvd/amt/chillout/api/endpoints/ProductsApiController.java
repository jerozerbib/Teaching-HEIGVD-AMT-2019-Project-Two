package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ProductsApi;
import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.InlineObject;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ProductsApiController implements ProductsApi {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    /**
     * Creates a new Product from the ProductInput of the Swagger file with a POST method request
     * @param product ProductInput that gives all the needed information necessary to create a Product
     * @return a new Object
     */
    public ResponseEntity<Object> createProduct(@ApiParam(value = "", required = true) @Valid @RequestBody ProductInput product) throws ApiException {
        ProductEntity productEntity = productService.createProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(productEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all the Products with a GET method request
     * @return all the products
     */
    public ResponseEntity<List<ProductOutput>> getProducts() throws ApiException {
        List<ProductOutput> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<ProductOutput> getProductById(@ApiParam(value = "The email of the Product",required=true) @PathVariable("id") Long id) throws ApiException {
        ProductEntity entity = productService.getProductById(id);
        return ResponseEntity.ok(productService.toProduct(entity));
    }

    public ResponseEntity<ProductOutput> updateProduct(@ApiParam(value = "The id of the Product",required=true) @PathVariable("id") Long id, @ApiParam(value = ""  )  @Valid @RequestBody InlineObject fields) throws ApiException{
        productService.updateProduct(id, fields);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> deleteProductById(@ApiParam(value = "The id of the Product",required=true) @PathVariable("id") Long id) throws ApiException {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
