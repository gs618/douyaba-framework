package com.github.douyaba.exception.api;

import com.github.douyaba.exception.api.dto.HelloDTO;
import com.github.douyaba.exception.core.entity.ExceptionDO;
import com.github.douyaba.exception.core.repository.ExceptionRepository;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author s.c.gao
 */
@RestController
@RequestMapping("/test")
public class ExceptionApi {

    @Resource
    private ExceptionRepository exceptionRepository;

    @GetMapping("/exceptions")
    public List<ExceptionDO> list() {
        return exceptionRepository.findAll();
    }

    @PostMapping("/exception")
    public String save(@RequestBody ExceptionDO exceptionDO) {
        ExceptionDO saved = exceptionRepository.save(exceptionDO);
        return saved.getId();
    }

    @GetMapping("/test1")
    public Mock get1(){
        return new Mock().setM1("Hello").setM2("world");
    }

    @GetMapping("/test2")
    public void get2(){
        System.out.println("Hello world");
    }

    @Data
    @Accessors(chain = true)
    public static class Mock{
        String m1;
        String m2;
    }

    @PostMapping("/jackson-test")
    public void jacksonTest(@RequestBody HelloDTO helloDTO){
        System.out.println(helloDTO);
    }
}
