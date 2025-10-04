package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Repo.CategoryRepo;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

}
