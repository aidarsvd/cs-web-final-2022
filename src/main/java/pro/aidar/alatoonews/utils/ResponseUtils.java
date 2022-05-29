package pro.aidar.alatoonews.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


public class ResponseUtils {

    public static String getImageUrl(String filename) {
        return filename != null ? ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/media/")
                .path(filename)
                .toUriString() : null;
    }

}
