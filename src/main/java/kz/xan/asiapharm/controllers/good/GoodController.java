package kz.xan.asiapharm.controllers.good;

import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.converters.GoodCommandToGood;
import kz.xan.asiapharm.converters.GoodToGoodCommand;
import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/good")
@Controller
public class GoodController {
    private final GoodService goodService;
    private final GoodToGoodCommand toGoodCommand;
    private final GoodCommandToGood fromGoodCommand;

    public GoodController(GoodService goodService, GoodToGoodCommand toGoodCommand, GoodCommandToGood fromGoodCommand) {
        this.goodService = goodService;
        this.toGoodCommand = toGoodCommand;
        this.fromGoodCommand = fromGoodCommand;
    }

    @RequestMapping("/show-all")
    public String listGoods(Model model){
        model.addAttribute("goods", goodService.findAll());

        return "good/goods";
    }

    @RequestMapping("/{id}/delete")
    public String deleteGood(@PathVariable Long id){
        goodService.deleteById(id);

        return "redirect:/good/show-all";
    }

    @GetMapping("/{id}/showInfo")
    public String getGood(@PathVariable Long id, Model model){
        model.addAttribute("goodCommand", toGoodCommand.convert(goodService.findById(id)));

        return "good/singleInfo";
    }

    @GetMapping("/{id}/editInfo")
    public String getEditGood(@PathVariable Long id, Model model){
        model.addAttribute("goodCommand", toGoodCommand.convert(goodService.findById(id)));

        return "good/editInfo";
    }

    @PostMapping("/{id}/editInfo")
    public String postEditedGood(@PathVariable Long id, @ModelAttribute("goodCommand") GoodCommand goodCommand){
        Good oldGood = goodService.findById(id);
        Good editedGood = fromGoodCommand.convert(goodCommand);
        if(oldGood != null){
            assert editedGood != null;
            editedGood.setImage(oldGood.getImage());
        }
        goodService.save(editedGood);

        return "redirect:"+id+"/showInfo";
    }

    @GetMapping("/newInfo")
    public String getNewGood(Model model){
        model.addAttribute("goodCommand", new GoodCommand());

        return "good/newInfo";
    }

    @PostMapping("/newInfo")
    public String postNewGood(@ModelAttribute("goodCommand") GoodCommand goodCommand){
        Good newGood = fromGoodCommand.convert(goodCommand);
        Good savedGood = goodService.save(newGood);

        return "redirect:"+savedGood.getId()+"/showInfo";
    }

    @GetMapping("/search")
    public String searchGood(@RequestParam("searchText") String searchText, Model model){
        Set<GoodCommand> goodCommands = goodService.findCommandsByNameContaining(searchText);
        model.addAttribute("goods", goodCommands);

        return "good/goods";
    }
}
