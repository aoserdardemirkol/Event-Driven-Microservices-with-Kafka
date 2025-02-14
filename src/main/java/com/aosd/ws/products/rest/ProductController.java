package com.aosd.ws.products.rest;

import com.aosd.ws.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/async")
    public ResponseEntity<String> createProductAsync(@RequestBody CreateProductRestModel product) {
        String productId = productService.createProductAsync(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @PostMapping("/sync")
    public ResponseEntity<Object> createProductSync(@RequestBody CreateProductRestModel product) {
        String productId;
        try {
            productId = productService.createProductSync(product);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(), e.getMessage(), "/products/sync"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
