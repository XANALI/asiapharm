package kz.xan.asiapharm.controllers;

import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.services.CategoryService;
import kz.xan.asiapharm.services.CategoryTypeService;
import kz.xan.asiapharm.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final CategoryService categoryService;
    private final CategoryTypeService categoryTypeService;
    private final GoodService goodService;

    public IndexController(CategoryService categoryService, CategoryTypeService categoryTypeService, GoodService goodService) {
        this.categoryService = categoryService;
        this.categoryTypeService = categoryTypeService;
        this.goodService = goodService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("goods", goodService.findAllCommands());

        return "index";
    }

    @RequestMapping({"/categories"})
    public String selectCategory(Model model){
        model.addAttribute("categories", categoryService.findCategoryCommands());
        model.addAttribute("categoryTypes", categoryTypeService.findAll());

        return "categories";
    }

    @RequestMapping("/registration")
    public String getNewUser(Model model){
        model.addAttribute("userCommand", new UserCommand());

        return "registration";
    }
}
