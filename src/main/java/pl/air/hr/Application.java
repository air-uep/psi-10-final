package pl.air.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.air.hr.init.DataLoader;
import pl.air.hr.system.HRSystem;

@SpringBootApplication
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired private DataLoader dataLoader;
    @Autowired private HRSystem hrSystem;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.insertData();
        hrSystem.run();
    }

}
