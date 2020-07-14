package kz.xan.asiapharm.controllers.good;

import kz.xan.asiapharm.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/good")
@Controller
public class GoodController {
    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @RequestMapping("/show-all")
    public String listGoods(Model model){
        model.addAttribute("goods", goodService.findAll());

        return "good/goods";
    }
}
