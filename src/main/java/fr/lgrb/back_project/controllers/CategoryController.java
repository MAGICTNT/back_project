package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.entity.Category;
import fr.lgrb.back_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> allCategory(){
        return categoryService.getAllCategorys();
    }
}
