package kz.xan.asiapharm.controllers.order;

import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.domain.Order;
import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.services.GoodService;
import kz.xan.asiapharm.services.OrderService;
import kz.xan.asiapharm.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final GoodService goodService;

    public OrderController(OrderService orderService, UserService userService, GoodService goodService) {
        this.orderService = orderService;
        this.userService = userService;
        this.goodService = goodService;
    }

    @RequestMapping("/show-all")
    public String listOrders(Model model){
        model.addAttribute("orders", orderService.findAll());

        return "order/orders";
    }

    @RequestMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteById(id);

        return "redirect:/order/show-all";
    }

    @GetMapping("/newOrder")
    public String getNewOrder(@RequestParam("good_id") Long good_id, @RequestParam("quantity") Integer quantity, Authentication authentication, Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserCommand userCommand = userService.findUserCommandByUsername(userDetails.getUsername());

        GoodCommand goodCommand = goodService.findCommandByID(good_id);

        model.addAttribute("userCommand", userCommand);
        model.addAttribute("goodCommand", goodCommand);
        model.addAttribute("quantity", quantity);

        return "order/newOrder";
    }

    @PostMapping("/newOrder")
    public String postNewOrder(@RequestParam("good_id") Long good_id,
                               @RequestParam("user_id") Long user_id,
                               @RequestParam("good_quantity") Integer quantity){

        Good good = goodService.findById(good_id);
        good.setQuantity(good.getQuantity() - quantity);
        good = goodService.save(good);
        User user = userService.findById(user_id);

        Order order = new Order();
        order.setGood(good);
        order.setUser(user);
        orderService.save(order);

        return "redirect:show-all";
    }
}
