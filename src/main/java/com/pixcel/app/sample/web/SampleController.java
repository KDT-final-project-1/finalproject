package com.pixcel.app.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/accordion")
    public String accordion() {
        return "sample/accordion";
    }

    @GetMapping("/alerts")
    public String alerts() {
        return "sample/alerts";
    }
    @GetMapping("/test")
    public String testWelcome() {
        return "sample/testWelcome";
    }
}