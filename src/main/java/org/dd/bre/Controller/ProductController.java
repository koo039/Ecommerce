package org.dd.bre.Controller;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductDetailsDto;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/category")
    public ResponseEntity<ProductPageResponse> getAllProductByCategoryHandler(@RequestParam String categoryName,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "12") int size,
                                                                           @RequestParam(required = false) String sortBy,
                                                                           @RequestParam(required = false) String direction){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productService.getAllProductsByCategory(categoryName,pageable,sortBy,direction), HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ProductDetailsDto> getProductByNameHandler(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductPageResponse> getAllProductsHandler(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable, sortBy, direction));
    }


}
