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
//    }

}
