package com.vm.springnative;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    @GetMapping("/")
    public String hello() {
        return "Time: " + java.time.LocalDateTime.now().toString();
    }
}
