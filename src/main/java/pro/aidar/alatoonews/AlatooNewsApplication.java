package pro.aidar.alatoonews;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.entity.user.Role;
import pro.aidar.alatoonews.model.entity.user.Roles;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.service.news.NewsService;
import pro.aidar.alatoonews.model.service.user.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class AlatooNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlatooNewsApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(NewsService service) {
//        return args ->  service.save(
//                News.builder()
//                        .title("Hello World")
//                        .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
//                        .build()
//        );
//    }

}
