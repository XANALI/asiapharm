package kz.xan.asiapharm.controllers.categoryType;

import kz.xan.asiapharm.converters.CategoryTypeCommandToCategoryType;
import kz.xan.asiapharm.converters.CategoryTypeToCategoryTypeCommand;
import kz.xan.asiapharm.services.CategoryTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category-type")
public class CategoryTypeController {
    private final CategoryTypeService categoryTypeService;
    private final CategoryTypeToCategoryTypeCommand toCommandConverter;
    private final CategoryTypeCommandToCategoryType fromCommandConverter;

    public CategoryTypeController(CategoryTypeService categoryTypeService, CategoryTypeToCategoryTypeCommand toCommandConverter, CategoryTypeCommandToCategoryType fromCommandConverter) {
        this.categoryTypeService = categoryTypeService;
        this.toCommandConverter = toCommandConverter;
        this.fromCommandConverter = fromCommandConverter;
    }

    @RequestMapping("/show-all")
    public String listCategoryTypes(Model model){
        model.addAttribute("categoryTypes",categoryTypeService.findAll());

        return "categoryType/categoryTypes";
    }

    @RequestMapping("/{id}/show")
    public String getCategoryType(@PathVariable Long id, Model model){
        model.addAttribute("categoryType", toCommandConverter.convert(categoryTypeService.findById(id)));

        return "categoryType/singleInfo";
    }

    @RequestMapping("/{id}/edit")
    public String getCategoryTypeEdit(@PathVariable Long id, Model model){
        model.addAttribute("categoryType", toCommandConverter.convert(categoryTypeService.findById(id)));

        return "categoryType/editInfo";
    }
}
