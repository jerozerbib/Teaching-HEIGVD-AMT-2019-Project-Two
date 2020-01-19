package ch.heigvd.amt.chillout.api.service;

import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.InlineObject;
import ch.heigvd.amt.chillout.api.model.ProductInput;
import ch.heigvd.amt.chillout.api.model.ProductOutput;
import ch.heigvd.amt.chillout.entities.ProductEntity;
import ch.heigvd.amt.chillout.repositories.ProductRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity createProduct(ProductInput productInput) throws ApiException{
        ProductEntity productEntity = toProductEntity(productInput);

        if (productRepository.existsById(productEntity.getId())) {
            throw new ApiException(HttpStatus.CONFLICT,"Product already exists.");
        }

        productRepository.save(productEntity);
        return productEntity;
    }

    public void deleteProductById(Long id) throws ApiException {
        ProductEntity productEntity = getProductById(id);
        productRepository.delete(productEntity);
    }

    public List<ProductOutput> getProducts() throws ApiException {
        List<ProductOutput> products = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            products.add(toProduct(productEntity));
        }
        return products;
    }

    public void updateProduct(Long id, @Valid InlineObject fields) throws ApiException {
        ProductEntity productEntity = getProductById(id);

        String description = fields.getDescription();
        String name = fields.getName();
        String unitPrice = fields.getUnitPrice();

        if(description != null) {
            productEntity.setDescription(description);
        }
        if (name != null){
            productEntity.setName(name);
        }
        if (unitPrice != null){
            productEntity.setUnitPrice(Double.parseDouble(unitPrice));
        }
        productRepository.save(productEntity);
    }

    public ProductEntity getProductById(Long id) throws ApiException {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND,"User not found");
        }
        return productEntity;
    }

    /**
     * Converts a ProductInput into a ProductEntity
     * @param product to convert
     * @return a ProductEntity
     */
    public ProductEntity toProductEntity(ProductInput product) {
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
    public ProductOutput toProduct(ProductEntity entity) {
        ProductOutput product = new ProductOutput();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setUnitPrice(entity.getUnitPrice());
        product.setDescription(entity.getDescription());
        return product;
    }
}
