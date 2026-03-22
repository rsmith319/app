package ca.grp1.sabs.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppApplication.class);
        app.setHeadless(true);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}

