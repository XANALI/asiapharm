package kz.xan.asiapharm.controllers.category;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.converters.CategoryCommandToCategory;
import kz.xan.asiapharm.converters.CategoryToCategoryCommand;
import kz.xan.asiapharm.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/category")
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryToCategoryCommand toCommandconverter;
    private final CategoryCommandToCategory fromCommandConverter;

    public CategoryController(CategoryService categoryService, CategoryToCategoryCommand toCommandconverter, CategoryCommandToCategory fromCommandConverter) {
        this.categoryService = categoryService;
        this.toCommandconverter = toCommandconverter;
        this.fromCommandConverter = fromCommandConverter;
    }

    @RequestMapping("/show-all")
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.findAll());

        return "category/categories";
    }

    @RequestMapping("/{id}")
    public String getCategory(@PathVariable Long id, Model model){
        CategoryCommand categoryCommand = toCommandconverter.convert(categoryService.findById(id));
        model.addAttribute("categoryCommand", categoryCommand);

        return "category/singleInfo";
    }

    @PostMapping("/changeCategory")
    public String changeCategory(@ModelAttribute CategoryCommand categoryCommand){
        CategoryCommand savedCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);

        return "redirect:show-all";
    }

    @RequestMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);

        return "redirect:/category/show-all";
    }
}
