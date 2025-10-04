package org.dd.bre.Controller;

import org.dd.bre.Dto.ProductDetailsDto;
import org.dd.bre.Service.ProductService;
import org.dd.bre.model.Product;
import org.dd.bre.Dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/products")
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
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryHandler(@RequestParam String categoryName){
        return new ResponseEntity<>(productService.getAllProductsByCategory(categoryName), HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ProductDetailsDto> getProductByNameHandler(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
    }
    @GetMapping("/asc_price")
    public ResponseEntity<List<ProductDTO>> getAllProductAscHandler(){
        return new ResponseEntity<>(productService.getAllProductsAsc(), HttpStatus.OK);
    }
    @GetMapping("/desc_price")
    public ResponseEntity<List<ProductDTO>> getAllProductDescHandler(){
        return new ResponseEntity<>(productService.getAllProductsDesc(), HttpStatus.OK);
    }
    @GetMapping("/high_rating")
    public ResponseEntity<List<ProductDTO>> getAllProductByHighRateHandler(){
        return new ResponseEntity<>(productService.getAllProductsHighRate(), HttpStatus.OK);
    }

}
