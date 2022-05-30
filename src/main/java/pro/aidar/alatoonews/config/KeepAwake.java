package pro.aidar.alatoonews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class KeepAwake {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 20 * 60 * 1000)
    public void keepAwake() {
        String result = restTemplate.getForObject("https://alatoo-news.herokuapp.com/ping", String.class);
        System.out.println(result);
    }

}
