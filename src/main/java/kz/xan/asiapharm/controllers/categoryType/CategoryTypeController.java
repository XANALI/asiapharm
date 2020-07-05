package kz.xan.asiapharm.controllers.categoryType;

import kz.xan.asiapharm.services.CategoryTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category-type")
public class CategoryTypeController {
    private final CategoryTypeService categoryTypeService;

    public CategoryTypeController(CategoryTypeService categoryTypeService) {
        this.categoryTypeService = categoryTypeService;
    }

    @RequestMapping("/show-all")
    public String listCategoryTypes(Model model){
        model.addAttribute("categoryTypes",categoryTypeService.findAll());

        return "categoryTypes";
    }
}
