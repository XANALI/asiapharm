package kz.xan.asiapharm.controllers.category;

import kz.xan.asiapharm.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/category")
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/show-all")
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.findAll());

        return "categories";
    }
}
