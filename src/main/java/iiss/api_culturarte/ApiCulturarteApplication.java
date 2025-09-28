package iiss.api_culturarte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "iiss.api_culturarte",
    "Controller",
    "Service",
    "Repositorios"
})
public class ApiCulturarteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCulturarteApplication.class, args);
    }

}
