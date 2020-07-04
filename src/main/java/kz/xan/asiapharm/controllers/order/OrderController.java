package kz.xan.asiapharm.controllers.order;

import kz.xan.asiapharm.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/show-all")
    public String listOrders(Model model){
        model.addAttribute("orders", orderService.findAll());

        return "orders";
    }
}
