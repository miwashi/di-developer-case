package net.miwashi.di;

import com.apptastic.rssreader.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class DiDeveloperCaseApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiDeveloperCaseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DiDeveloperCaseApplication.class, args);
    }

    //@GetMapping("/index")
    public String index() {
        LOGGER.trace("->index");
        System.out.println("Index");
        LOGGER.trace("<-index");
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        LOGGER.trace("->error");
        System.out.println("Error");
        LOGGER.trace("<-error");
        return "error";
    }

    @ExceptionHandler({ Throwable.class })
    public void handleException() {
        System.out.println("Exception");
    }
}
