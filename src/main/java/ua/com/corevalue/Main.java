package ua.com.corevalue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.corevalue.controller.MainController;
import ua.com.corevalue.model.DatabaseManager;
import ua.com.corevalue.view.View;

import static java.lang.System.exit;

@SpringBootApplication
public class Main  implements CommandLineRunner {

    @Autowired
    private View view;
    @Autowired
    private DatabaseManager manager;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        MainController controller = new MainController(view, manager);
        controller.run();
    }
}
