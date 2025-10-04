package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductDTO;
import org.dd.bre.Dto.ProductDetailsDto;
import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Mapper.ProductDetailsMapper;
import org.dd.bre.Mapper.ProductMapper;
import org.dd.bre.Repo.CategoryRepo;
import org.dd.bre.Repo.ProductRepo;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ProductMapper productMapper;
    private final ProductDetailsMapper productDetailsMapper;


    public ProductDetailsDto getProductByName(String productName) {
        Product product = productRepo.findByProductName(productName);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productName);
        }
        return productDetailsMapper.mapToProductDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }

    protected List<ProductDTO> getAllProductsByWishList(List<WishList> wishLists) {
        List<Long> in = wishLists.stream().map(WishList::getId).toList();
        List<Product> products = productRepo.findAllByWishLists_IdIn(in);
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }

    public List<ProductDTO> getAllProductsByCategory(String categoryName) {
        Category category = categoryRepo.findByCategoryName(categoryName);
        if (category == null) {
            throw new ProductNotFoundException("Category not found: " + categoryName);
        }

        List<Product> products = productRepo.findAllByCategory(category);
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }


    public List<ProductDTO> getAllProductsAsc(){
        List<Product> products = productRepo.findAllAsc();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }
    public List<ProductDTO> getAllProductsDesc(){
        List<Product> products = productRepo.findAllDesc();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }
    public List<ProductDTO> getAllProductsHighRate(){
        List<Product> products = productRepo.findAllByHighRate();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .toList();
    }
}
