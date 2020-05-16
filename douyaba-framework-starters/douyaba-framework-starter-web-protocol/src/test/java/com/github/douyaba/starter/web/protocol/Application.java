package com.github.douyaba.starter.web.protocol;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@Slf4j
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        log.info(" ========== " + applicationContext.getId() + " started ==========");
    }

    @GetMapping("/get1")
    public String get(){
        log.info("Return get");
        return "get";
    }

    @GetMapping("/get2")
    public void getVoid(){
        log.info("Get return void");
    }

    @PostMapping("/post")
    public void post(){
        log.info("Post return void");
    }

    @DeleteMapping("/delete")
    public void delete(){
        log.info("Delete return void");
    }

    @PutMapping("/put")
    public void put(){
        log.info("Put return void");
    }
}
