package kz.xan.asiapharm.bootstrap;

import kz.xan.asiapharm.services.CategoryService;
import kz.xan.asiapharm.services.GoodService;
import kz.xan.asiapharm.services.OrderService;
import kz.xan.asiapharm.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final GoodService goodService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public DataLoader(UserService userService, GoodService goodService, CategoryService categoryService, OrderService orderService) {
        this.userService = userService;
        this.goodService = goodService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
