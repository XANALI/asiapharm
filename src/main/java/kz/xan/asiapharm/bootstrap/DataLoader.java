package kz.xan.asiapharm.bootstrap;

import kz.xan.asiapharm.domain.*;
import kz.xan.asiapharm.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final GoodService goodService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final CategoryTypeService categoryTypeService;

    public DataLoader(UserService userService, GoodService goodService, CategoryService categoryService, OrderService orderService, CategoryTypeService categoryTypeService) {
        this.userService = userService;
        this.goodService = goodService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.categoryTypeService = categoryTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("Alikhan");
        user1.setLastName("Ryskhan");
        user1.setUsername("xan");
        user1.setPassword("alikhan01");
        user1.setBirthDate(LocalDate.now());
        user1.setEmail("ryskhan.ali@gmail.com");
        user1.setRoles(Collections.singleton(ROLE.ROLE_ADMIN));
        user1.setMobileNum("87081843486");

        userService.save(user1);

        CategoryType categoryType = new CategoryType();
        categoryType.setName("Лекарственные средства");
        categoryType.setDescription("Medicines");

        categoryTypeService.save(categoryType);

        CategoryType vitamins = new CategoryType();
        vitamins.setName("Витамины и бады");
        vitamins.setDescription("Витамины");

        categoryTypeService.save(vitamins);

        CategoryType fashion = new CategoryType();
        fashion.setName("Красота и гигиена");
        fashion.setDescription("Крема и тд");

        categoryTypeService.save(fashion);

        Category category = new Category();
        category.setName("Жаропонижающие");
        category.setDescription("smth");
        category.setCategoryType(categoryType);

        categoryService.save(category);

        Category sportAndFitness = new Category();
        sportAndFitness.setName("Sport and Fitness");
        sportAndFitness.setDescription("sport");
        sportAndFitness.setCategoryType(vitamins);

        categoryService.save(sportAndFitness);

        Category protection = new Category();
        protection.setName("Protection from nasekomiy");
        protection.setDescription("protect blen");
        protection.setCategoryType(fashion);

        categoryService.save(protection);

        Good good = new Good();
        good.setName("Nurofen");
        good.setPrice(765);
        good.setQuantity(20);
        good.setDescription("smth");
        good.setCategory(category);

        goodService.save(good);

        Order order = new Order();
        order.setUser(user1);
        order.setGood(good);
        order.setDescription("smth");

        orderService.save(order);
    }
}
