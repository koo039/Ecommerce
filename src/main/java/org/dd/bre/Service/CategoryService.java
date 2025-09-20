package org.dd.bre.Service;

import org.dd.bre.Repo.CategoryRepo;
import org.dd.bre.Exception.CategoryNotFoundException;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();

        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found");
        }

        return categories;
    }

}
