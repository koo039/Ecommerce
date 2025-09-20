package org.dd.bre.Controller;

import org.dd.bre.Service.ProductService;
import org.dd.bre.model.Product;
import org.dd.bre.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductHandler(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryHandler(@RequestParam(required = false) String categoryName){
        return new ResponseEntity<>(productService.getAllProductsByCategory(categoryName), HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductByNameHandler(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
    }
}
