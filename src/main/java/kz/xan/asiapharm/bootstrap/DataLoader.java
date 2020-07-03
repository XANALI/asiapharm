package kz.xan.asiapharm.bootstrap;

import kz.xan.asiapharm.domain.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
    }
}
