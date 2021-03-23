package com.twitter.reader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final Twitter4j twitter4j;

    public HomeController() {
        twitter4j = new Twitter4j();
    }


    @GetMapping("/")
    public String getHtml(){
        return twitter4j.getHtml();
    }
}
