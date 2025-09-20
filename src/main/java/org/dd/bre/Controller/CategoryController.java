package org.dd.bre.Controller;

import org.dd.bre.Service.CategoryService;
import org.dd.bre.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategoriesHandler(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }


}
